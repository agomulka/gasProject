package com.gomulka.ola;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GasData implements Comparable<GasData> {
    private String dateAsString;
    private LocalDate date;
    private int temperature, value;
    private WeatherData weatherData;
    private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
    private int heatSeason;

    // gas value for today
    public GasData(int value) {
        this.value = value;
        this.date = LocalDate.now();
        this.dateAsString = date.format(formatter);
        this.weatherData = new WeatherData("Katowice", dateAsString);
        this.temperature = downloadTemperature(dateAsString);
    }

    // gas value for specific date
    public GasData(int value, String dateAsString) {
        this.value = value;
        this.dateAsString = dateAsString;
        int[] partsDate = partsDate(dateAsString);
        this.date = LocalDate.of(partsDate[0], partsDate[1], partsDate[2]);
        this.weatherData = new WeatherData("Katowice", dateAsString);
        this.temperature = downloadTemperature(dateAsString);
    }

    private int[] partsDate(String date){
        String[] split = date.split("-");
        int[] parts = new int[3];
        parts[0] = Integer.valueOf(split[0]);
        parts[1] = Integer.valueOf(split[1]);
        parts[2] = Integer.valueOf(split[2]);
        return parts;
    }

    private int downloadTemperature(String dateAsString) {
        WeatherAPI weatherAPI = new WeatherAPI(weatherData);
        try {
            return weatherAPI.getTemperature();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public String toString() {
        return "GasData " +
                "value = " + value +
                " from : " + dateAsString +
                " temperature : " + temperature;
    }


    @Override
    public int compareTo(GasData o) {
        return dateAsString.compareTo(o.dateAsString);
    }

    public int getValue() {
        return value;
    }

    public String getDateAsString() {
        return dateAsString;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getYear(){
        return String.valueOf(date.getYear());
    }
}
