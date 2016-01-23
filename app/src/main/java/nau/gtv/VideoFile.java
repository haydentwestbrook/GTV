package nau.gtv;

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

    public VideoFile(long latitude, long longitude, String idString){
        this.latitude = latitude;
        this.longitude = longitude;
        this.idString = idString;
    }

    public long getLatitude(){
        return this.latitude;
    }

    public long getLongitude(){
        return this.longitude;
    }
}
