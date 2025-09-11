package com.project.Restaurant.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {

//    @NotBlank(message = "Street number is required.")
//    @Pattern(regexp =  "^[0-9]{1,5}[a-zA-Z]?$", message = "Invalid street number format")
    private String streetNo;

    @NotBlank(message = "Street name is required.")
    private String streetName;

    private String unit;

    @NotBlank(message = "City is required.")
    private String city;

    @NotBlank(message = "State is required.")
    private String state;

    @NotBlank(message = "Postal Code is required.")
    private String postalCode;

    @NotBlank(message = "Country is required.")
    private String country;

//    public AddressDto(String streetNo, String streetName, String unit, String city, String state, String postalCode, String country) {
//        this.streetNo = streetNo;
//        this.streetName = streetName;
//        this.unit = unit;
//        this.city = city;
//        this.state = state;
//        this.postalCode = postalCode;
//        this.country = country;
//    }
//
//    public AddressDto() {
//    }
//
//    public String getStreetNo() {
//        return streetNo;
//    }
//
//    public void setStreetNo(String streetNo) {
//        this.streetNo = streetNo;
//    }
//
//    public String getStreetName() {
//        return streetName;
//    }
//
//    public void setStreetName(String streetName) {
//        this.streetName = streetName;
//    }
//
//    public String getUnit() {
//        return unit;
//    }
//
//    public void setUnit(String unit) {
//        this.unit = unit;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
//
//    public String getPostalCode() {
//        return postalCode;
//    }
//
//    public void setPostalCode(String postalCode) {
//        this.postalCode = postalCode;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
}
