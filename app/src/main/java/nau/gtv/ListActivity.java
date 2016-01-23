package nau.gtv;

import android.Manifest;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView videoList;
    private Button newVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        populateList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_video, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This method is called when swipe refresh is pulled down
     */
    @Override
    public void onRefresh() {
        fetchVideos();
    }

    private void populateList() {

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        fetchVideos();

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                        fetchVideos();
                                    }
                                }
        );
    }

    public void onNewVideoClick(View v) {
        //Start camera activity
        Intent videoActivity = new Intent(ListActivity.this, VideoActivity.class);
        startActivity(videoActivity);
    }

    private void fetchVideos() {
        // showing refresh animation before making http call
        swipeRefreshLayout.setRefreshing(true);

        videoList = (ListView) findViewById(R.id.videos_list);

        List<VideoFile> videos = new ArrayList<VideoFile>();
        videos.add(new VideoFile(0, 0, "Video One", Uri.parse("content://media/external/video/media/81790")));

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        VideoAdapter videoAdapter = new VideoAdapter(this, R.layout.row_layout, videos);
        videoList.setAdapter(videoAdapter);

        swipeRefreshLayout.setRefreshing(false);
    }

    private class VideoAdapter extends ArrayAdapter<VideoFile> {

        private AdapterView.OnItemClickListener onListClick= new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent video = new Intent(ListActivity.this, ActivitySplash.class);
                Bundle bundle = new Bundle();
                bundle.putString("Uri", getItem(position).getUri().toString());
                video.putExtras(bundle);
                startActivity(video);
            }
        };

        public VideoAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public VideoAdapter(Context context, int resource, List<VideoFile> items) {
            super(context, resource, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.row_layout, null);
            }

            VideoFile p = getItem(position);

            if(p != null) {
                TextView text = (TextView) v.findViewById(R.id.video_name);

                if(text != null) {
                    text.setText(p.getId());
                }
            }

            ListView videoList = (ListView) findViewById(R.id.videos_list);
            videoList.setOnItemClickListener(onListClick);

            return v;
        }
    }
}
