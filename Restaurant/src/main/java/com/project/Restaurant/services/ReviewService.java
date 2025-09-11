package com.project.Restaurant.services;

import com.project.Restaurant.domain.ReviewCreateUpdateRequest;
import com.project.Restaurant.domain.entities.Review;
import com.project.Restaurant.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ReviewService {

    Review createReview(User author, String restaurantId, ReviewCreateUpdateRequest review);

    Page<Review> listReviews(String restaurantId, Pageable pageable);

    Optional<Review> getRestaurantReview(String restaurantId, String reviewId);

    public Review updateReview(User user, String restaurantId, String reviewId,
                               ReviewCreateUpdateRequest updatedReview);

    void deleteReview(String restaurantId, String reviewId);
}
