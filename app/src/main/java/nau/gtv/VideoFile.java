package nau.gtv;

import android.net.Uri;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Grant on 1/22/2016.
 */
public class VideoFile {
    private long latitude;
    private long longitude;
    //to uniquely identify each video
    private String idString;
    private Uri storeLocation;

    public VideoFile(long latitude, long longitude, String idString, Uri location){
        this.latitude = latitude;
        this.longitude = longitude;
        this.idString = idString;
        this.storeLocation = location;
    }

    public long getLatitude(){
        return this.latitude;
    }

    public long getLongitude(){
        return this.longitude;
    }
}
