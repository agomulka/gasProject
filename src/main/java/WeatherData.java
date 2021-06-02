import java.time.LocalDate;

public class WeatherData {
    int temperature;
    String location;
    String date;

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


}
