/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherapp;

/**
 *
 * @author PC
 */
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        try {
            // Prompt user for city input
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter city name: ");
            String cityName = reader.readLine();

            // Create a WeatherService instance
            WeatherService weatherService = new WeatherService();

            // Fetch latitude and longitude for the given city
            double[] coordinates = weatherService.getCoordinates(cityName);

            if (coordinates != null) {
                double latitude = coordinates[0];
                double longitude = coordinates[1];

                // Fetch weather data
                String weatherData = weatherService.fetchWeatherData(latitude, longitude);

                // Fetch air pollution data
                String pollutionData = weatherService.fetchAirPollutionData(latitude, longitude);

                // Print timestamp
                System.out.println("Timestamp: " + weatherService.getCurrentTimestamp());

                // Show current weather conditions
                weatherService.getCurrentWeather(latitude, longitude);
                weatherService.showCurrentWeather(latitude, longitude);
                // Show sunrise and sunset time
                weatherService.getSunriseSunset(latitude, longitude);

                // Generate Notification for poor weather conditions
                weatherService.generateWeatherNotification(latitude, longitude);

                // Show Air Pollution data
                weatherService.showAirPollutionData(pollutionData);

                // Generate Notification for poor air quality
                weatherService.generateAirQualityNotification(latitude, longitude);

                // Show data about polluting gasses
                System.out.println("\nData about polluting gasses:\n ");
                weatherService.showPollutingGassesData(pollutionData);
                
                // Show weather forecast for 5 days
                System.out.println("\nWeather forecast for 5 days:\n ");
                weatherService.getWeatherForecast(weatherData);

            } else {
                System.out.println("Error: Unable to fetch coordinates for the city.");
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
