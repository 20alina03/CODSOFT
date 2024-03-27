/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author minal
 */
public class PoorWeatherNotification {
    public static void generate(JSONObject wind, JSONObject weather) throws JSONException {
        double windSpeed = wind.getDouble("speed");
        String mainWeather = weather.getString("main");

        if (windSpeed > 10.0) {
            System.out.println("Notification: High wind speeds detected. Caution advised.");
        }

        if ("Rain".equals(mainWeather) || "Snow".equals(mainWeather)) {
            System.out.println("Notification: Precipitation detected. Carry an umbrella!");
        }
    }
}
