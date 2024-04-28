/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Databases;
import UI.CityHolder;
import static UI.CityHolder.longitude;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
 import java.sql.*;  
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import org.json.JSONException;
import org.json.JSONObject;
import weatherapp.WeatherAPI;
import weatherapp.WeatherAPI;
/**
 *
 * @author alien
 */


public class SQLWeatherDataCache implements WeatherDataCache 
{  
   private Map<String, JSONObject> cache;

    private static final String API_KEY = "28728fa2214f564d9cabb0b76b183a23";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5";

        //cache = new HashMap<>();
    private Connection con1;
    private PreparedStatement insert;
    
   public boolean searchData(String cityName) throws ClassNotFoundException, SQLException {
    Connection con1 = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    boolean cityFound = false;
    
    try {
        Class.forName("com.mysql.jdbc.Driver");
        con1 = DriverManager.getConnection("jdbc:mysql://localhost/weather", "root", "m_1234gQ");
        
        // Create a prepared statement to query the database
        String query = "SELECT * FROM CurrentWeather WHERE city = ? AND DATE(timestamp) = CURDATE()";
        stmt = con1.prepareStatement(query);
        stmt.setString(1, cityName);
        
        // Execute the query
        rs = stmt.executeQuery();
        
        // Check if the city was found
        if (rs.next()) {
            cityFound = true;
        }
    } finally {
        // Close resources
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (con1 != null) {
            con1.close();
        }
    }
    
    return cityFound;
}
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
        public static String getApiKey() {
        return API_KEY;
    }

    // Getter method for BASE_URL
    public static String getBaseUrl() {
        return BASE_URL;
    }
        public double[] getCoordinates(String location) throws IOException {
        String encodedLocation = URLEncoder.encode(location, "UTF-8");
       String requestUrl = BASE_URL + "/weather?q=" + encodedLocation + "&APPID=" + API_KEY;

        try {
            JSONObject jsonResponse = fetchData(requestUrl);
            double latitude = jsonResponse.getJSONObject("coord").getDouble("lat");
            double longitude = jsonResponse.getJSONObject("coord").getDouble("lon");
            return new double[]{latitude, longitude};
        } catch (JSONException e) {
            throw new IOException("No results found for the location: " + location);
        }
        
        }
          public String getCurrentWeather(double latitude, double longitude1) throws IOException, JSONException {
    String url = WeatherAPI.getBaseUrl() + "/weather?lat=" + latitude + "&lon=" + longitude + "&appid=" + WeatherAPI.getApiKey();
     
    String temp=fetchData(url).toString();
       JSONObject jsonObject = new JSONObject(temp);
String cityName = (String)jsonObject.get("name");
    return cityName;
}
          
          
          
          
          
          
 /* insertion karo
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
          */
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
    
public static void main(String args[]){  
try{  
Class.forName("com.mysql.jdbc.Driver");  
//here sonoo is database name, root is username and password
    try (Connection con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/weather","root","m_1234gQ")) {
        //here sonoo is database name, root is username and password
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select * from emp");
        while(rs.next())
            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
    }
}
catch(ClassNotFoundException | SQLException e){ System.out.println(e);}  
}  
}