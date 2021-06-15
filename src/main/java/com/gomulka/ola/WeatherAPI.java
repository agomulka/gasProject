package com.gomulka.ola;

import com.fasterxml.jackson.databind.ObjectMapper;

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


public class WeatherAPI {
    private final String KEY = "d679749c7e07455dac8133338210705";
    private String LOCATION;
    private String DATE;
    private WeatherData weather;

    public WeatherAPI(WeatherData weather) {
        this.weather = weather;
        LOCATION = weather.getLocation();
        DATE = weather.getDate();
    }

    private String buildUrl(){
        String url = "";
        if(DATE.equals(String.valueOf(LocalDate.now()))) {
            url = "http://api.worldweatheronline.com/premium/v1/weather.ashx?key=" + KEY + "&q=" + LOCATION + "&num_of_days=1&date=" + DATE + "&tp=24&format=json";
        } else {
            url = "http://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=" + KEY + "&q=" + LOCATION + "&date=" + DATE + "&tp=24&format=json";
        }
        return url;
    }


    private Integer getWeather() throws IOException, InterruptedException {

        //String url = "http://api.openweathermap.org/data/2.5/weather?q="+LOCATION+"&appid="+KEY + "&units=metric";
        //  String url = "http://api.openweathermap.org/data/2.5/weather?q="+LOCATION+"&appid="+KEY+"&units=metric" ;//+ "&units=metric";
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
                            //   System.out.println(key + " : " + k2 + " : " + k3 + " :" + result.get(key).get(k2).get(i).get(k3));
                        }
                    }
                }
            }
        }
        return 0;
    }

    public Integer getTemperature() throws IOException, InterruptedException {
        return getWeather();
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        WeatherAPI weather = new WeatherAPI(new WeatherData("Katowice", "2021-05-14"));
        System.out.println(weather.buildUrl());
        WeatherAPI w2 = new WeatherAPI(new WeatherData("Katowice", "2020-05-14"));
        System.out.println(w2.buildUrl());
    }
}
