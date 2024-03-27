import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;


public class AirPollutionApp {

    public static void main(String[] args) {
        try {
            // Prompt user for city input
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter city name: ");
            String cityName = reader.readLine();

            // Fetch latitude and longitude for the given city
            double[] coordinates = GeoCodingApi.getCoordinates(cityName);

            if (coordinates != null) {
                double latitude = coordinates[0];
                double longitude = coordinates[1];

                // Fetch weather data
                String weatherData = WeatherApiClient.fetchWeatherData(latitude, longitude);

                // Print timestamp for weather data
                System.out.println("Weather Data Timestamp: " + TimestampGenerator.getCurrentTimestamp());

                // Parse JSON weather data
                JSONObject weatherJson = new JSONObject(weatherData);

                // Extract and print individual weather details
                printWeatherDetails(weatherJson);

                // Fetch air pollution data
                String pollutionData = AirPollutionApiClient.fetchAirPollutionData(latitude, longitude);

                // Print timestamp for air pollution data
                System.out.println("Air Pollution Data Timestamp: " + TimestampGenerator.getCurrentTimestamp());

                // Parse JSON air pollution data
                JSONObject pollutionJson = new JSONObject(pollutionData);

                // Extract and print individual air pollution details
                printPollutionDetails(pollutionJson);

            } else {
                System.out.println("Error: Unable to fetch coordinates for the city.");
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }


    private static void printPollutionDetails(JSONObject json) throws JSONException {
        JSONObject components = json.getJSONObject("list").getJSONObject("components");
        System.out.println("CO: " + components.getDouble("co") + "ppm");
        System.out.println("NO: " + components.getDouble("no")+ "ppm");
        System.out.println("NO2: " + components.getDouble("no2")+ "ppm");
        System.out.println("O3: " + components.getDouble("o3")+ "ppm");
        System.out.println("SO2: " + components.getDouble("so2")+ "ppm");
        System.out.println("PM2.5: " + components.getDouble("pm2_5")+ "ppm");
        System.out.println("PM10: " + components.getDouble("pm10")+ "ppm");
        System.out.println("NH3: " + components.getDouble("nh3")+ "ppm");
    }
}
