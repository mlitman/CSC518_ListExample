package com.example.awesomefat.csc518_listexample.yelp;

public class Restaurant
{
    private String name;
    private String phoneNumber;
    private String address;
    private String url;
    private String imageURL;
    private String reviewCount;
    private String rating;

    public Restaurant(String name, String phoneNumber, String address, String url, String imageURL, String reviewCount, String rating)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.url = url;
        this.imageURL = imageURL;
        this.reviewCount = reviewCount;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getUrl() {
        return url;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getReviewCount() {
        return reviewCount;
    }

    public String getRating() {
        return rating;
    }
}
