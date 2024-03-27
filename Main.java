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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Enter city name: ");
            String cityName = reader.readLine();

            WeatherService weatherService = new WeatherService();

            // Fetch latitude and longitude for the given city
            double[] coordinates = weatherService.getCoordinates(cityName);

            if (coordinates != null && coordinates.length == 2) {
                double latitude = coordinates[0];
                double longitude = coordinates[1];

                // Show sunrise and sunset time
                System.out.println("Showing sunrise and sunset time:");
                weatherService.getSunriseSunset(latitude, longitude);

                // Show weather forecast for 5 days
                System.out.println("\nShowing weather forecast for 5 days:");
                weatherService.getWeatherForecast(latitude, longitude);
            } else {
                System.out.println("Unable to fetch coordinates for the city: " + cityName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

