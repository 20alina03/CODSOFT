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
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

class WeatherAPI {
    private static final String API_KEY = "28728fa2214f564d9cabb0b76b183a23";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5";

    // Cache for storing fetched data
    private static final Map<String, JSONObject> cache = new HashMap<>();

    // Getter method for API_KEY
    public static String getApiKey() {
        return API_KEY;
    }

    // Getter method for BASE_URL
    public static String getBaseUrl() {
        return BASE_URL;
    }

    // Method to fetch data from the API or cache
    public static JSONObject fetchData(String url) throws IOException, JSONException {
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
}