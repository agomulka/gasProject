package com.gomulka.ola.model;

import com.gomulka.ola.dao.WeatherDao;

import java.time.LocalDate;

public class GasData implements Comparable<GasData> {
    private final String dateAsString;
    private final LocalDate date;
    private final int temperature, value;
    private final WeatherData weatherData;
    private int heatSeason;
    private WeatherDao weatherDao;

    // gas value for specific date
    public GasData(int value, String dateAsString) {
        this.value = value;
        this.dateAsString = dateAsString;
        int[] partsDate = partsDate(dateAsString);
        this.date = LocalDate.of(partsDate[0], partsDate[1], partsDate[2]);
        this.weatherData = new WeatherData("Katowice", dateAsString);
        weatherDao = new WeatherDao(weatherData);
        this.temperature = downloadTemperature(dateAsString);
    }

    private int[] partsDate(String date) {
        String[] split = date.split("-");
        int[] parts = new int[3];
        parts[0] = Integer.valueOf(split[0]);
        parts[1] = Integer.valueOf(split[1]);
        parts[2] = Integer.valueOf(split[2]);
        return parts;
    }

    private int downloadTemperature(String dateAsString) {
        try {
            return weatherDao.getTemperature();
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

    public String getYear() {
        return String.valueOf(date.getYear());
    }
}
