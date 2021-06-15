import java.time.LocalDate;

public class WeatherData {
    private int temperature;
    private String location;
    private String date;

    public WeatherData(int temperature) {
        this.temperature = temperature;
    }

    public WeatherData(String location, String date) {
        this.location = location;
        this.date = date;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }
}
