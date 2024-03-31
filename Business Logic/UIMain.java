import UI.UIstart;      /////uncomment this while running GUI

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
///////package weatherapp;        //// comment while ruuning GUI
/**
 *
 * @author alien
 */

/////comment all these while running GUI
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import org.json.JSONException;
public class UIMain {
    ////-------------------------------------------------GUI-------------------------------------------(must uncomment UI import at top)
    public static void main(String[] args) {
        UIstart UI= new UIstart();
        UI.setVisible(true);
    }
    
    ////-------------------------------------------------Terminal UI-------------------------------------------
//    public static void main(String[] args) throws JSONException {
//        try {
//            // Prompt user for city input
//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//            System.out.print("Enter city name: ");
//            String cityName = reader.readLine();
//
//            // Create a WeatherService instance
//            WeatherService weatherService = new WeatherService();
//
//            // Fetch latitude and longitude for the given city
//            double[] coordinates = weatherService.getCoordinates(cityName);
//
//            if (coordinates != null) {
//                double latitude = coordinates[0];
//                double longitude = coordinates[1];
//
//                // Fetch weather data
//                String weatherData = weatherService.fetchWeatherData(latitude, longitude);
//
//                // Fetch air pollution data
//                String pollutionData = weatherService.fetchAirPollutionData(latitude, longitude);
//
//                // Print timestamp
//                System.out.println("Timestamp: " + WeatherService.getCurrentTimestamp());
//
//                // Show current weather conditions
//                weatherService.getCurrentWeather(latitude, longitude);
//                weatherService.showCurrentWeather(latitude, longitude);
//                // Show sunrise and sunset time
//                weatherService.getSunriseSunset(latitude, longitude);
//
//                // Generate Notification for poor weather conditions
//                weatherService.generateWeatherNotification(latitude, longitude);
//
//                // Show Air Pollution data
//                weatherService.showAirPollutionData(pollutionData);
//
//                // Generate Notification for poor air quality
//                weatherService.generateAirQualityNotification(latitude, longitude);
//
//                // Show data about polluting gasses
//                System.out.println("\nData about polluting gasses:\n ");
//                weatherService.showPollutingGassesData(pollutionData);
//                
//                // Show weather forecast for 5 days
//                System.out.println("\nWeather forecast for 5 days:\n ");
//                weatherService.getWeatherForecast(weatherData);
//
//            } else {
//                System.out.println("Error: Unable to fetch coordinates for the city.");
//            }
//        } catch (IOException e) {
//        }
//        
//        
//    }
}
