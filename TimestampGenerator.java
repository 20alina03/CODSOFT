import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampGenerator {

    // Method to get current timestamp
    public static String getCurrentTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        return sdf.format(now);
    }
}
