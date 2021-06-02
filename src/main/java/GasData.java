import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GasData implements Comparable<GasData> {
    int value;
    String dateAsString;
    LocalDate date;
    int temperature;
    WeatherData weatherData;

    // gas value for today
    public GasData(int value) {
        this.value = value;
        this.date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        this.dateAsString = date.format(formatter);

        this.weatherData = new WeatherData("Katowice", dateAsString);
        this.temperature = downloadTemperature(dateAsString);
    }

    // gas value for specific date
    public GasData(int value, String dateAsString) {
        this.value = value;
        this.dateAsString = dateAsString;
        this.weatherData = new WeatherData("Katowice", dateAsString);
        this.temperature = downloadTemperature(dateAsString);
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
                " temperature : "+ temperature;
    }


    @Override
    public int compareTo(GasData o) {
        return dateAsString.compareTo(o.dateAsString);
        //return date.compareTo(o.date);
    }
}
