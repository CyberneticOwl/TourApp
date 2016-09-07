package com.example.sriramhariharan.maptest;

import com.google.android.gms.maps.model.LatLng;

public class GooglePlace {
    private String name;
    private String category;
    private String rating;
    private String open;
    public LatLng coord;

    public GooglePlace() {
        this.name = "";
        this.rating = "";
        this.open = "";
        this.setCategory("");
        coord = null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoord(double Lat, double Long){
        coord = new LatLng(Lat,Long);
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    public void setOpenNow(String open) {
        this.open = open;
    }

    public String getOpenNow() {
        return open;
    }
}
