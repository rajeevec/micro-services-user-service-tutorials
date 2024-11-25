package com.user.service.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users") // Specify the table name
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primary key with auto-generation
    @Column(name = "user_id") // Column name in the database
    private Long userId;

    @Column(name = "first_name") // Column name in the database
    private String firstName;

    @Column(name = "middle_name") // Column name in the database
    private String middleName;

    @Column(name = "last_name") // Column name in the database
    private String lastName;

    @Column(name = "user_email") // Column name in the database
    private String userEmail;

    @Column(name = "user_contact_number") // Column name in the database
    private String userContactNumber;

    @Column(name = "user_about") // Column name in the database
    private String userAbout;

    @Transient
    private List<Rating> ratingList = new ArrayList<>();

    // Getter and Setter methods
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserContactNumber() {
        return userContactNumber;
    }

    public void setUserContactNumber(String userContactNumber) {
        this.userContactNumber = userContactNumber;
    }

    public String getUserAbout() {
        return userAbout;
    }

    public void setUserAbout(String userAbout) {
        this.userAbout = userAbout;
    }

    // Getter for ratingList
    public List<Rating> getRatingList() {
        return ratingList;
    }

    // Setter for ratingList
    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }
}
