package com.techease.pfd.Controller;

/**
 * Created by Adam Noor on 14-Nov-17.
 */

public class Pesh_FD_Model {

    private String id;
    private String RestName;
    private String ImageUrl;
    private String Lat;
    private String Lng;
    private String Location;
    private String DeliveryNo;

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    private String Rating;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestName() {
        return RestName;
    }

    public void setRestName(String restName) {
        RestName = restName;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLng() {
        return Lng;
    }

    public void setLng(String lng) {
        Lng = lng;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getDeliveryNo() {
        return DeliveryNo;
    }

    public void setDeliveryNo(String deliveryNo) {
        DeliveryNo = deliveryNo;
    }



}
