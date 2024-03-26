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
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

public class WeatherService {
    private Map<String, JSONObject> cache;

    public WeatherService() {
        cache = new HashMap<>();
    }

    // Method to fetch data from the API or cache
    private JSONObject fetchData(String url) throws IOException, JSONException {
        if (cache.containsKey(url)) {
            return cache.get(url);
        } else {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                cache.put(url, jsonResponse);
                return jsonResponse;
            } else {
                throw new IOException("Failed to fetch data. Response code: " + responseCode);
            }
        }
    }

    // Method to fetch latitude and longitude for a city
    public double[] getCoordinates(String cityName) throws IOException {
        String encodedCityName = URLEncoder.encode(cityName, "UTF-8");
        String requestUrl = WeatherAPI.getBaseUrl() + "/weather?q=" + encodedCityName + "&APPID=" + WeatherAPI.getApiKey();

        try {
            JSONObject jsonResponse = fetchData(requestUrl);
            double latitude = jsonResponse.getJSONObject("coord").getDouble("lat");
            double longitude = jsonResponse.getJSONObject("coord").getDouble("lon");
            return new double[]{latitude, longitude};
        } catch (JSONException e) {
            throw new IOException("No results found for the city: " + cityName);
        }
    }

    // Method to get sunrise and sunset times for a location
    public void getSunriseSunset(double latitude, double longitude) {
        try {
            String url = WeatherAPI.getBaseUrl() + "/weather?lat=" + latitude + "&lon=" + longitude + "&appid=" + WeatherAPI.getApiKey();
            JSONObject response = fetchData(url);
            JSONObject sys = response.getJSONObject("sys"); // Move this line inside try block
            long sunriseEpoch = sys.getLong("sunrise");
            long sunsetEpoch = sys.getLong("sunset");
            LocalDateTime sunriseTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(sunriseEpoch), ZoneId.systemDefault());
            LocalDateTime sunsetTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(sunsetEpoch), ZoneId.systemDefault());
            System.out.println("Sunrise time: " + sunriseTime);
            System.out.println("Sunset time: " + sunsetTime);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    // Method to get weather forecast for 5 days
    public void getWeatherForecast(double latitude, double longitude) {
        try {
            String url = WeatherAPI.getBaseUrl() + "/forecast?lat=" + latitude + "&lon=" + longitude + "&appid=" + WeatherAPI.getApiKey();
            JSONObject response = fetchData(url);
            JSONArray forecastList = response.getJSONArray("list");
            for (int i = 0; i < forecastList.length(); i++) {
                JSONObject forecast = forecastList.getJSONObject(i);
                long timestamp = forecast.getLong("dt");
                LocalDateTime forecastTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
                JSONObject main = forecast.getJSONObject("main");
                double temperatureKelvin = main.getDouble("temp"); // Temperature in Kelvin
                int temperatureCelsius = (int) (temperatureKelvin - 273.15); // Convert Kelvin to Celsius and cast to int
                JSONArray weatherArray = forecast.getJSONArray("weather");
                JSONObject weather = weatherArray.getJSONObject(0);
                String description = weather.getString("description"); // Get weather description
                System.out.println("Forecast for " + forecastTime + ": Temperature: " + temperatureCelsius + "°C, Description: " + description);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
