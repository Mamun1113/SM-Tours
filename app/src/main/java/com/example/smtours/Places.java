package com.example.smtours;

public class Places {
    private int serial;
    private String placename;
    private String location;
    private String distance;
    private String transport;
    private String costperperson;
    private String duration;
    private String food;
    private String part;
    private String imageurl;

    public Places(int serial, String placename, String location, String distance, String transport, String costperperson, String duration, String food, String part, String imageurl) {
        this.serial = serial;
        this.placename = placename;
        this.location = location;
        this.distance = distance;
        this.transport = transport;
        this.costperperson = costperperson;
        this.duration = duration;
        this.food = food;
        this.part = part;
        this.imageurl = imageurl;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getPlacename() {
        return placename;
    }

    public void setPlacename(String placename) {
        this.placename = placename;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getCostperperson() {
        return costperperson;
    }

    public void setCostperperson(String costperperson) {
        this.costperperson = costperperson;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }
}
