/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package wetahernotification;

/**
 *
 * @author Fatima
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

public class WeatherNotification {

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

                // Fetch air pollution data
                String pollutionData = WeatherApiClient.fetchAirPollutionData(latitude, longitude);

                // Print timestamp
                System.out.println("Timestamp: " + TimestampGenerator.getCurrentTimestamp());

                // Parse JSON weather data
                JSONObject jsonWeather = new JSONObject(weatherData);

                // Parse JSON air pollution data
                JSONObject jsonPollution = new JSONObject(pollutionData);

                // Extract and print individual weather details
                                System.out.println("Humidity: " + main.getInt("humidity"));

                JSONObject wind = jsonWeather.getJSONObject("wind");
                System.out.println("Wind Speed: " + wind.getDouble("speed"));

                JSONObject clouds = jsonWeather.getJSONObject("clouds");
                System.out.println("Cloudiness: " + clouds.getInt("all"));

                JSONObject sys = jsonWeather.getJSONObject("sys");
                System.out.println("Sunrise Time: " + new Date(sys.getLong("sunrise") * 1000));
                System.out.println("Sunset Time: " + new Date(sys.getLong("sunset") * 1000));


                // Extract and print individual pollution details
                JSONArray list = jsonPollution.getJSONArray("list");
if (list.length() > 0) {
    JSONObject mainPollution = list.getJSONObject(0).getJSONObject("main");
    int aqi = mainPollution.getInt("aqi"); // AQI is inside the "main" object, not "components"
    System.out.println("Air Quality Index (AQI): " + aqi);
    
  } else {
    System.out.println("No air pollution data available.");
}


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
        // Generate weather notification
        JSONObject wind = jsonWeather.getJSONObject("wind");
        JSONArray weatherArray = jsonWeather.getJSONArray("weather");
        if (weatherArray.length() > 0) {
            JSONObject weatherDescription = weatherArray.getJSONObject(0);
            WeatherNotification.generate(wind, weatherDescription);
        }

       private static void generateNotifications(JSONObject jsonWeather, JSONObject jsonPollution) throws JSONException {
        // Generate weather notification
        JSONObject wind = jsonWeather.getJSONObject("wind");
        JSONArray weatherArray = jsonWeather.getJSONArray("weather");
        if (weatherArray.length() > 0) {
            JSONObject weatherDescription = weatherArray.getJSONObject(0);
            WeatherNotification.generate(wind, weatherDescription);
        }
}    
}

