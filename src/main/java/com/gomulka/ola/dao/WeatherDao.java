package com.gomulka.ola.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gomulka.ola.model.WeatherData;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class WeatherDao {
    private final String KEY = "ed07d906dd514668b09143422211506";
    //            "d679749c7e07455dac8133338210705";
    private final String LOCATION;
    private final String DATE;
    private final WeatherData weather;

    public WeatherDao(WeatherData weather) {
        this.weather = weather;
        LOCATION = weather.getLocation();
        DATE = weather.getDate();
    }

    private String buildUrl() {
        String url = "";
        if (DATE.equals(String.valueOf(LocalDate.now()))) {
            url = "http://api.worldweatheronline.com/premium/v1/weather.ashx?key=" + KEY + "&q=" + LOCATION + "&num_of_days=1&date=" + DATE + "&tp=24&format=json";
        } else {
            url = "http://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=" + KEY + "&q=" + LOCATION + "&date=" + DATE + "&tp=24&format=json";
        }
        return url;
    }


    private Integer getAvgTemperature() throws IOException, InterruptedException {
        String url = buildUrl();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofMinutes(2))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Map<String, LinkedHashMap<String, ArrayList<LinkedHashMap<String, Object>>>> result = new ObjectMapper().readValue(response.body(), HashMap.class);

        for (String key : result.keySet()) {
            for (String k2 : result.get(key).keySet()) {
                for (int i = 0; i < result.get(key).get(k2).size(); i++) {
                    for (String k3 : result.get(key).get(k2).get(i).keySet()) {
                        if (k3.equals("avgtempC")) {
                            return Integer.valueOf(String.valueOf(result.get(key).get(k2).get(i).get(k3)));
                        }
                    }
                }
            }
        }
        return 0;
    }

    public Integer getTemperature() throws IOException, InterruptedException {
        return getAvgTemperature();
    }

    public String getUrl() {
        return buildUrl();
    }


}
