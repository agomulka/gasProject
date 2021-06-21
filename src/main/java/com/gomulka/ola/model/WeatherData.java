package com.gomulka.ola.model;

public class WeatherData {
    private final String location;
    private final String date;


    public WeatherData(String location, String date) {
        this.location = location;
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }
}
