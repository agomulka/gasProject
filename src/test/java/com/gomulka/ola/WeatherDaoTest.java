package com.gomulka.ola;

import com.gomulka.ola.dao.WeatherDao;
import com.gomulka.ola.model.WeatherData;
import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;

public class WeatherDaoTest {

    @Test
    public void buildCorrectUrl() {
        WeatherDao weather = new WeatherDao(new WeatherData("Katowice", "2021-05-14"));
        String correctUrl = "http://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=ed07d906dd514668b09143422211506&q=Katowice&date=2021-05-14&tp=24&format=json";
        assertEquals(correctUrl, weather.getUrl());
    }


    @Test
    public void buildUrlForToday() {
        String today = String.valueOf(LocalDate.now());
        WeatherDao weather = new WeatherDao(new WeatherData("Katowice", today));
        String correctUrl = "http://api.worldweatheronline.com/premium/v1/weather.ashx?key=ed07d906dd514668b09143422211506&q=Katowice&num_of_days=1&date=" + today + "&tp=24&format=json";
        assertEquals(correctUrl, weather.getUrl());
    }

}