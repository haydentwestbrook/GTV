package nau.gtv;
import java.lang.Math;
import android.content.Context;
import android.media.MediaPlayer;
public class HelperFuntions {
    public double distance(double x1, double y1, double x2, double y2)
    {
        double radius = 6371000; // metres
        double lat1 = Math.toRadians(x1);
        double lat2 = Math.toRadians(x2);
        double long1 = Math.toRadians(y1);
        double long2 = Math.toRadians(y2);
        double deltaPhi = lat2 - lat1;
        double deltaLamda = long2 -long1;
        double a = Math.sin(deltaPhi/2) * Math.sin(deltaPhi/2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(deltaLamda/2) * Math.sin(deltaLamda/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return radius * c;
    }


}
