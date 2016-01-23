package nau.gtv;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.VideoView;

public class ActivitySplash extends Activity {

    private String videoUri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent video = getIntent();
        Bundle bundle = video.getExtras();
        videoUri = bundle.getString("Uri");

        try {
            splashPlayer();
        } catch (Exception ex) {
            jumpMain();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    public void splashPlayer() {
        VideoView videoHolder = new VideoView(this);
        videoHolder.setVideoURI(Uri.parse(Environment.getRootDirectory().getAbsolutePath() + "/myVideo.mp4"));
        setContentView(videoHolder);
        //Uri video = Uri.parse(videoUri);
        //videoHolder.setVideoURI(video);
        videoHolder.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                jumpMain();
            }

        });
        videoHolder.start();
        videoHolder.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ((VideoView) v).stopPlayback();
                jumpMain();
                return true;
            }
        });
    }

    private synchronized void jumpMain() {
        Intent intent = new Intent(ActivitySplash.this, ListActivity.class);
        startActivity(intent);
        finish();
    }
}
