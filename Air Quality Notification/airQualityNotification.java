/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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

public class airQualityNotification {
    public static void generate(JSONObject mainPollution) throws JSONException {
        int aqi = mainPollution.getInt("aqi");

        if (aqi > 150) {
            System.out.println("Notification: Unhealthy air quality. Limit outdoor activity.");
        } else if (aqi > 100) {
            System.out.println("Notification: Poor air quality for sensitive groups.");
        }
    }
}
