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
import static UI.CityHolder.longitude;
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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherService {
    private Map<String, JSONObject> cache;

    public WeatherService(double latitude, double longitude1) {
        cache = new HashMap<>();
    }

    public WeatherService() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    
    public String getCurrentWeather(double latitude, double longitude1) throws IOException, JSONException {
    String url = WeatherAPI.getBaseUrl() + "/weather?lat=" + latitude + "&lon=" + longitude + "&appid=" + WeatherAPI.getApiKey();
    return fetchData(url).toString();
}
    
    // Method to fetch latitude and longitude for a city
    public double[] getCoordinates(String location) throws IOException {
        String encodedLocation = URLEncoder.encode(location, "UTF-8");
        String requestUrl = WeatherAPI.getBaseUrl() + "/weather?q=" + encodedLocation + "&APPID=" + WeatherAPI.getApiKey();

        try {
            JSONObject jsonResponse = fetchData(requestUrl);
            double latitude = jsonResponse.getJSONObject("coord").getDouble("lat");
            double longitude = jsonResponse.getJSONObject("coord").getDouble("lon");
            return new double[]{latitude, longitude};
        } catch (JSONException e) {
            throw new IOException("No results found for the location: " + location);
        }
    }

    // Method to fetch weather data
    public String fetchWeatherData(double latitude, double longitude) throws IOException, JSONException {
        String url = WeatherAPI.getBaseUrl() + "/forecast?lat=" + latitude + "&lon=" + longitude + "&appid=" + WeatherAPI.getApiKey();
        JSONObject weatherData = WeatherAPI.fetchData(url);

        // Add placeholder for "list" key if it doesn't exist
        if (!weatherData.has("list")) {
            weatherData.put("list", new JSONArray());
        }

        return weatherData.toString();
    }




    // Method to fetch air pollution data
    public String fetchAirPollutionData(double latitude, double longitude) throws IOException, JSONException {
        String url = WeatherAPI.getBaseUrl() + "/air_pollution?lat=" + latitude + "&lon=" + longitude + "&appid=" + WeatherAPI.getApiKey();
        return fetchData(url).toString();
    }

    // Method to show current weather conditions
    public void showCurrentWeather(double latitude, double longitude) {
        try {
            JSONObject jsonWeather = fetchdata(latitude, longitude);
            if (jsonWeather.has("main")) {
                JSONObject main = jsonWeather.getJSONObject("main");

                DecimalFormat df = new DecimalFormat("#.##"); // Format to display two decimal places

                double temperatureKelvin = main.getDouble("temp");
                double feelsLikeKelvin = main.getDouble("feels_like");
                double tempMinKelvin = main.getDouble("temp_min");
                double tempMaxKelvin = main.getDouble("temp_max");

                double temperatureCelsius = temperatureKelvin - 273.15;
                double feelsLikeCelsius = feelsLikeKelvin - 273.15;
                double tempMinCelsius = tempMinKelvin - 273.15;
                double tempMaxCelsius = tempMaxKelvin - 273.15;

                System.out.println("Temperature: " + df.format(temperatureCelsius) + "°C");
                System.out.println("Feels Like: " + df.format(feelsLikeCelsius) + "°C");
                System.out.println("Minimum Temperature: " + df.format(tempMinCelsius) + "°C");
                System.out.println("Maximum Temperature: " + df.format(tempMaxCelsius) + "°C");

                System.out.println("Pressure: " + main.getInt("pressure"));
                System.out.println("Humidity: " + main.getInt("humidity"));

                if (jsonWeather.has("wind")) {
                    JSONObject wind = jsonWeather.getJSONObject("wind");
                    System.out.println("Wind Speed: " + wind.getDouble("speed"));
                } else {
                    System.out.println("Wind speed data not available.");
                }

                if (jsonWeather.has("clouds")) {
                    JSONObject clouds = jsonWeather.getJSONObject("clouds");
                    System.out.println("Cloudiness: " + clouds.getInt("all"));
                } else {
                    System.out.println("Cloudiness data not available.");
                }
            } else {
                System.out.println("Weather data not available.");
            }
        } catch (IOException | JSONException e) {
        }
    }
    public static String getCurrentTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        return sdf.format(now);
    }
    
    public JSONObject fetchdata(double latitude, double longitude) throws IOException, JSONException {
        String url = WeatherAPI.getBaseUrl() + "/weather?lat=" + latitude + "&lon=" + longitude + "&appid=" + WeatherAPI.getApiKey();
        return fetchData(url);
    }

    
    public void getSunriseSunset(double latitude, double longitude) throws JSONException {
        try {
            JSONObject jsonWeather = fetchdata(latitude, longitude);
            if (jsonWeather.has("sys")) {
                JSONObject sys = jsonWeather.getJSONObject("sys");
                long sunriseEpoch = sys.getLong("sunrise");
                long sunsetEpoch = sys.getLong("sunset");
                LocalDateTime sunriseTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(sunriseEpoch), ZoneId.systemDefault());
                LocalDateTime sunsetTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(sunsetEpoch), ZoneId.systemDefault());
                System.out.println("Sunrise time: " + sunriseTime);
                System.out.println("Sunset time: " + sunsetTime);
            } else {
                System.out.println("Sunrise and sunset data not available.");
            }
        } catch (IOException e) {
        }
    }



    // Method to get weather forecast for 5 days
    public void getWeatherForecast(String weatherData) throws JSONException {
        JSONObject jsonWeather = new JSONObject(weatherData);
        if (jsonWeather.has("list")) {
            JSONArray forecastList = jsonWeather.getJSONArray("list");
            if (forecastList.length() > 0) {
                for (int i = 0; i < forecastList.length(); i++) {
                    JSONObject forecast = forecastList.getJSONObject(i);
                    long timestamp = forecast.getLong("dt");
                    LocalDateTime forecastTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
                    JSONObject main = forecast.getJSONObject("main");
                    double temperatureKelvin = main.getDouble("temp");
                    int temperatureCelsius = (int) (temperatureKelvin - 273.15);
                    JSONArray weatherArray = forecast.getJSONArray("weather");
                    JSONObject weather = weatherArray.getJSONObject(0);
                    String description = weather.getString("description");
                    System.out.println("Forecast for " + forecastTime + ": Temperature: " + temperatureCelsius + "°C, Description: " + description);
                }
            } else {
                System.out.println("No forecast data available.");
            }
        } else {
            System.out.println("No forecast data available.");
        }
    }



    // Method to show air pollution data
    public void showAirPollutionData(String pollutionData) throws JSONException {
        JSONObject jsonPollution = new JSONObject(pollutionData);
        JSONArray list = jsonPollution.getJSONArray("list");
        if (list.length() > 0) {
            JSONObject mainPollution = list.getJSONObject(0).getJSONObject("main");
            int aqi = mainPollution.getInt("aqi");
            System.out.println("Air Quality Index (AQI): " + aqi);
        } else {
            System.out.println("No air pollution data available.");
        }
    }

    public void generateWeatherNotification(double latitude, double longitude) {
    try {
        JSONObject jsonWeather = fetchdata(latitude, longitude);
        
        if (jsonWeather.has("wind") && jsonWeather.has("weather")) {
            JSONObject wind = jsonWeather.getJSONObject("wind");
            JSONArray weatherArray = jsonWeather.getJSONArray("weather");
            
            if (weatherArray.length() > 0) {
                JSONObject weatherDescription = weatherArray.getJSONObject(0);
                double windSpeed = wind.getDouble("speed");
                String mainWeather = weatherDescription.getString("main");
    
                if (windSpeed > 1.0) {
                    System.out.println("Notification: High wind speeds detected. Caution advised.");
                }
    
                if ("Rain".equals(mainWeather) || "Snow".equals(mainWeather)) {
                    System.out.println("Notification: Precipitation detected. Carry an umbrella!");
                }
            } else {
                System.out.println("Weather description data not available.");
            }
        } else {
            System.out.println("Wind and weather data not available.");
        }
    } catch (IOException | JSONException e) {
        e.printStackTrace();
    }
}

    public void generateAirQualityNotification(double latitude, double longitude) {
        try {
            String pollutionData = fetchAirPollutionData(latitude, longitude);
            JSONObject jsonPollution = new JSONObject(pollutionData);
            JSONArray list = jsonPollution.getJSONArray("list");
            if (list.length() > 0) {
                JSONObject mainPollution = list.getJSONObject(0).getJSONObject("main");
                int aqi = mainPollution.getInt("aqi");
                
                if (aqi > 150) {
                    System.out.println("Notification: Unhealthy air quality. Limit outdoor activity.");
                } else if (aqi >= 5 && aqi <= 150) {
                    System.out.println("Notification: Poor air quality for sensitive groups.");
                } else {
                    System.out.println("Notification: Air quality is satisfactory, and air pollution poses little or no risk.");
                }
            } else {
                System.out.println("Air Quality Index (AQI) not available.");
            }
        } catch (IOException | JSONException e) {
        }
    }



    // Method to show polluting gases data
    public void showPollutingGassesData(String pollutionData) throws JSONException {
        JSONObject jsonPollution = new JSONObject(pollutionData);
        JSONArray list = jsonPollution.getJSONArray("list");
        if (list.length() > 0) {
            JSONObject components = list.getJSONObject(0).getJSONObject("components");
            System.out.println("CO: " + components.getDouble("co") + "ppm");
            System.out.println("NO: " + components.getDouble("no") + "ppm");
            System.out.println("NO2: " + components.getDouble("no2") + "ppm");
            System.out.println("O3: " + components.getDouble("o3") + "ppm");
            System.out.println("SO2: " + components.getDouble("so2") + "ppm");
            System.out.println("PM2.5: " + components.getDouble("pm2_5") + "ppm");
            System.out.println("PM10: " + components.getDouble("pm10") + "ppm");
            System.out.println("NH3: " + components.getDouble("nh3") + "ppm");
        } else {
            System.out.println("No air pollution data available.");
        }
    }
}