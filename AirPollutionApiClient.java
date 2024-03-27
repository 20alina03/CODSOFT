import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AirPollutionApiClient {

    public static final String OPENWEATHERMAP_API_KEY = "YOUR_API_KEY"; // Replace "YOUR_API_KEY" with your actual API key

    public static String fetchAirPollutionData(double lat, double lon) {
        String apiUrl = "https://api.openweathermap.org/data/2.5/air_pollution?lat=" + lat + "&lon=" + lon + "&appid=" + OPENWEATHERMAP_API_KEY;

        try {
            URL url = new URL(apiUrl);
            String jsonData = stream(url);

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonData);

            return jsonObject.toJSONString(); // Return JSON string representing air pollution data
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String stream(URL url) {
        StringBuilder json = new StringBuilder();
        try (InputStream input = url.openStream()) {
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(isr);

            int c;
            while ((c = reader.read()) != -1) {
                json.append((char) c);
            }
            return json.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }
}
