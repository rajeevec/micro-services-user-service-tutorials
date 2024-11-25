package com.user.service.entities;

public class Rating {

    private Long ratingId;
    private Long userId;
    private Long hotelId;
    private Integer rating;
    private String feedback;

    private Hotel hotel;

    // Getter and Setter for ratingId
    public Long getRatingId() {
        return ratingId;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }

    // Getter and Setter for userId
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // Getter and Setter for hotelId
    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    // Getter and Setter for rating
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    // Getter and Setter for feedback
    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    // Getter for hotel
    public Hotel getHotel() {
        return hotel;
    }

    // Setter for hotel
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

}
