/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author ayesha
 */
// WeatherApp.java (Main Application)
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Date;
import org.json.JSONArray;

public class airNotificationMain { // Renamed for clarity

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
                JSONObject jsonWeather = new JSONObject(weatherData); // Assuming JSON format

                // Fetch air pollution data
                String pollutionData = WeatherApiClient.fetchAirPollutionData(latitude, longitude);
                JSONObject jsonPollution = new JSONObject(pollutionData); // Assuming JSON format

                // Generate and print notifications for poor weather and air quality
                generateNotifications(jsonWeather, jsonPollution);
            } else {
                System.out.println("Error: Unable to fetch coordinates for the city.");
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
private static void generateNotifications(JSONObject jsonWeather, JSONObject jsonPollution) throws JSONException {
                JSONArray list = jsonPollution.getJSONArray("list");
        if (list.length() > 0) {
            JSONObject mainPollution = list.getJSONObject(0).getJSONObject("main");
            AirQualityNotification.generate(mainPollution);
        } else {
            System.out.println("Air Quality Index (AQI) not available.");
        }
}
}

