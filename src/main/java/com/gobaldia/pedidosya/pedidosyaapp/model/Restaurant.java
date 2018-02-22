package com.gobaldia.pedidosya.pedidosyaapp.model;

public class Restaurant {
    private String name;
    private String topCategories;
    private int rating;
    private String logo;
    private int deliveryTimeMaxMinutes;
    private String link;
    private String coordinates;

    public Restaurant(String name, String topCategories, int rating, String logo, int deliveryTimeMaxMinutes, String link, String coordinates) {
        this.name = name;
        this.topCategories = topCategories;
        this.rating = rating;
        this.logo = logo;
        this.deliveryTimeMaxMinutes = deliveryTimeMaxMinutes;
        this.link = link;
        this.coordinates = coordinates;
    }

    public Restaurant() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTopCategories(String topCategories) {
        this.topCategories = topCategories;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setDeliveryTimeMaxMinutes(int deliveryTimeMaxMinutes) {
        this.deliveryTimeMaxMinutes = deliveryTimeMaxMinutes;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public String getTopCategories() {
        return topCategories;
    }

    public int getRating() {
        return rating;
    }

    public String getLogo() {
        return logo;
    }

    public int getDeliveryTimeMaxMinutes() {
        return deliveryTimeMaxMinutes;
    }

    public String getLink() {
        return link;
    }

    public String getCoordinates() {
        return coordinates;
    }
}
