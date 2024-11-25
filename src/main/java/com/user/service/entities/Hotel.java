package com.user.service.entities;

public class Hotel {

    private Long hotelId;

    private String hotelName;
    private String hotelLocation;
    private String hotelAbout;

    // Getter and Setter for hotelId
    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    // Getter and Setter for hotelName
    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    // Getter and Setter for hotelLocation
    public String getHotelLocation() {
        return hotelLocation;
    }

    public void setHotelLocation(String hotelLocation) {
        this.hotelLocation = hotelLocation;
    }

    // Getter and Setter for hotelAbout
    public String getHotelAbout() {
        return hotelAbout;
    }

    public void setHotelAbout(String hotelAbout) {
        this.hotelAbout = hotelAbout;
    }
}
