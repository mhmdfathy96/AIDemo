package com.example.AIDemo;

public class Movie {
    private String title;
    private int year;
    private String language;
    private float rating;
    private String description;

    public Movie(String title, int year, String language, float rating, String description) {
        this.title = title;
        this.year = year;
        this.language = language;
        this.rating = rating;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
