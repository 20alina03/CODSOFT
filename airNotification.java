/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author ayesha
 */

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Date;
import org.json.JSONArray;

public class airNotification {

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
                JSONObject main = jsonWeather.getJSONObject("main");

                double temperatureKelvin = main.getDouble("temp");
                double feelsLikeKelvin = main.getDouble("feels_like");
                double tempMinKelvin = main.getDouble("temp_min");
                double tempMaxKelvin = main.getDouble("temp_max");

                DecimalFormat df = new DecimalFormat("#.##"); // Format to display two decimal places

               
                JSONObject wind = jsonWeather.getJSONObject("wind");
               
                JSONObject clouds = jsonWeather.getJSONObject("clouds");
                
                JSONObject sys = jsonWeather.getJSONObject("sys");
                

                                JSONArray list = jsonPollution.getJSONArray("list");
                if (list.length() > 0) {
                JSONObject mainPollution = list.getJSONObject(0).getJSONObject("main");
                int aqi = mainPollution.getInt("aqi"); // AQI is inside the "main" object, not "components"
                System.out.println("Air Quality Index (AQI): " + aqi);
    
                 JSONObject components = list.getJSONObject(0).getJSONObject("components");
                }

            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

          // Generate air quality notification
        JSONArray list = jsonPollution.getJSONArray("list");
        if (list.length() > 0) {
            JSONObject mainPollution = list.getJSONObject(0).getJSONObject("main");
            AirQualityNotification.generate(mainPollution);
        } else {
            System.out.println("Air Quality Index (AQI) not available.");
        }
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
