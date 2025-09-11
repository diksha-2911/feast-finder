package com.project.Restaurant.services.impl;

import com.project.Restaurant.domain.ReviewCreateUpdateRequest;
import com.project.Restaurant.domain.entities.Photo;
import com.project.Restaurant.domain.entities.Restaurant;
import com.project.Restaurant.domain.entities.Review;
import com.project.Restaurant.domain.entities.User;
import com.project.Restaurant.exceptions.RestaurantNotFoundException;
import com.project.Restaurant.exceptions.ReviewNotAllowedException;
import com.project.Restaurant.repositories.RestaurantRepository;
import com.project.Restaurant.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final RestaurantRepository restaurantRepository;

    @Override
    public Review createReview(User author, String restaurantId, ReviewCreateUpdateRequest review) {
        Restaurant restaurant = getRestaurantOrThrow(restaurantId);
        boolean hasExistingReview = restaurant.getReviews().stream()
                .anyMatch(r -> r.getWrittenBy().getId().equals(author.getId()));

        if (hasExistingReview){
            throw new ReviewNotAllowedException("USer has already reviewd this restaurant");
        }

        LocalDateTime now = LocalDateTime.now();

        List<Photo> photos = review.getPhotoIds().stream().map(url -> {
            Photo photo = new Photo();
            photo.setUrl(url);
            photo.setUploadDate(now);
            return photo;
        }).collect(Collectors.toList());

        String reviewId = UUID.randomUUID().toString();

        Review reviewToCreate = Review.builder()
                .id(reviewId)
                .content(review.getContent())
                .rating(review.getRating())
                .photos(photos)
                .datePosted(now)
                .dateEdited(now)
                .writtenBy(author)
                .build();

        restaurant.getReviews().add(reviewToCreate);

        updateRestaurantAverageRating(restaurant);

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return savedRestaurant.getReviews().stream()
                .filter(r -> reviewId.equals(r.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error retrieving created review"));

    }

    private Restaurant getRestaurantOrThrow(String restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(
                        "Restaurant with ID not found" + restaurantId
                ));
    }

    @Override
    public Page<Review> listReviews(String restaurantId, Pageable pageable) {
        Restaurant restaurant = getRestaurantOrThrow(restaurantId);
        List<Review> reviews = restaurant.getReviews();

        Sort sort = pageable.getSort();

        if(sort.isSorted()){
            Sort.Order order = sort.iterator().next();
            String property = order.getProperty();
            boolean isAscending = order.getDirection().isAscending();

            Comparator<Review> comparator = switch (property) {
                case "datePosted" -> Comparator.comparing(Review::getDatePosted);
                case "rating" -> Comparator.comparing(Review::getRating);
                default -> Comparator.comparing(Review::getDatePosted);
            };

            reviews.sort(isAscending ? comparator : comparator.reversed());
        } else {
            // Default sort by date descending
            reviews.sort(Comparator.comparing(Review::getDatePosted).reversed());
        }
        // Calculate pagination boundaries
        int start = (int) pageable.getOffset();

        // Handle empty pages
        if (start >= reviews.size()) {
            return new PageImpl<>(Collections.emptyList(), pageable, reviews.size());
        }

        int end = Math.min((start + pageable.getPageSize()), reviews.size());
        // Create the page of reviews
        return new PageImpl<>(reviews.subList(start, end), pageable, reviews.size());

    }

    @Override
    public Optional<Review> getRestaurantReview(String restaurantId, String reviewId) {
        Restaurant restaurant = getRestaurantOrThrow(restaurantId);
        return restaurant.getReviews().stream()
                .filter(r -> reviewId.equals(r.getId()))
                .findFirst();
    }

    @Override
    public Review updateReview(User user, String restaurantId, String reviewId, ReviewCreateUpdateRequest updatedReview) {
        // Get the restaurant or throw an exception if not found
        Restaurant restaurant = getRestaurantOrThrow(restaurantId);
        String currentUserId = user.getId();
        // Find the review and verify ownership
        List<Review> reviews = restaurant.getReviews();
        Review existingReview = reviews.stream()
                .filter(r -> r.getId().equals(reviewId) &&
                        r.getWrittenBy().getId().equals(currentUserId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));
        // Verify the 48-hour edit window
        if (LocalDateTime.now().isAfter(existingReview.getDatePosted().plusHours(48))) {
            throw new ReviewNotAllowedException("Review can no longer be edited (48-hour limit exceeded)");
        }
        // Update the review content
        existingReview.setContent(updatedReview.getContent());
        existingReview.setRating(updatedReview.getRating());
        existingReview.setDateEdited(LocalDateTime.now());

                // Update the review content
        existingReview.setContent(updatedReview.getContent());
        existingReview.setRating(updatedReview.getRating());
        existingReview.setDateEdited(LocalDateTime.now());
        // Update photos
        existingReview.setPhotos(updatedReview.getPhotoIds().stream()
                .map(url -> {
                    Photo photo = new Photo();
                    photo.setUrl(url);
                    photo.setUploadDate(LocalDateTime.now());
                    return photo;
                }).collect(Collectors.toList()));
        // Recalculate restaurant's average rating
        updateRestaurantAverageRating(restaurant);

                // Save and return the updated review
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return savedRestaurant.getReviews().stream()
                .filter(r -> r.getId().equals(reviewId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error retrieving updated review"));

    }

    public void updateRestaurantAverageRating(Restaurant restaurant){
        List<Review> reviews = restaurant.getReviews();

        if(reviews.isEmpty()) {
            restaurant.setAverageRating(0.0f);
        }else{
            double averageRating = reviews.stream().mapToDouble(Review::getRating)
                    .average()
                    .orElse(0.0);
            restaurant.setAverageRating((float) averageRating);
        }
    }

    @Override
    public void deleteReview(String restaurantId, String reviewId) {
        // Get the restaurant or throw an exception if not found
        Restaurant restaurant = getRestaurantOrThrow(restaurantId);
        // Filter out the review with the matching ID
        List<Review> filteredReviews = restaurant.getReviews().stream()
                .filter(review -> !reviewId.equals(review.getId()))
                .toList();
        // Update the restaurant's reviews
        restaurant.setReviews(filteredReviews);
        // Update the restaurant's average rating
        updateRestaurantAverageRating(restaurant);
        // Save the updated restaurant
        restaurantRepository.save(restaurant);
    }


}
