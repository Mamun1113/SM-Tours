package com.example.smtours;

public class PlaceList {
    private String imageurl;
    private String placename;
    private String location;
    private String nexttour;
    private String totalvisited;


    PlaceList() {

    }

    public PlaceList(String imageurl, String placename, String location, String nexttour, String totalvisited) {
        this.imageurl = imageurl;
        this.placename = placename;
        this.location = location;
        this.nexttour = nexttour;
        this.totalvisited = totalvisited;
    }

    public String getTotalvisited() {
        return totalvisited;
    }

    public void setTotalvisited(String totalvisited) {
        this.totalvisited = totalvisited;
    }

    public String getNexttour() {
        return nexttour;
    }

    public void setNexttour(String nexttour) {
        this.nexttour = nexttour;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getPlacename() {
        return placename;
    }

    public void setPlacename(String placename) {
        this.placename = placename;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}