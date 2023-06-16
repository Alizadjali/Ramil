package com.example.ramiladmin.models;

public class sosRecords {
    private double longitude;
    private double latitude;
    private String tripId;
    private String email;
    private String userId;
    private boolean isActive;

    public sosRecords() {
    }

    public sosRecords(double longitude, double latitude, String tripId, String email, String userId, boolean isActive) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.tripId = tripId;
        this.email = email;
        this.userId = userId;
        this.isActive = isActive;

    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getTripId() {
        return tripId;
    }
    public boolean getisActive() {
        return isActive;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getEmail() {
        return email;
    }
    public String getUserId() {
        return userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setisActive(boolean isActive) {
        this.isActive = isActive;
    }
}
