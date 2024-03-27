import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GeoCodingApi {

    static double[] getCoordinates(String cityName) throws JSONException {
        try {
            String urlStr = "https://api.openweathermap.org/geo/1.0/direct?q=" + URLEncoder.encode(cityName, "UTF-8") + "&limit=1&appid=" + WeatherApiClient.getApiKey();
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            StringBuilder response;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }
            conn.disconnect();
            
            JSONArray jsonArray = new JSONArray(response.toString());
            if (jsonArray.length() > 0) {
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                double lat = jsonObject.getDouble("lat");
                double lon = jsonObject.getDouble("lon");
                return new double[]{lat, lon};
            }
        } catch (IOException e) {
            System.err.println("Error: Unable to fetch coordinates for the city. " + e.getMessage());
        }
        return null;
    }
}
