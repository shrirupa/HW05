package com.example.sgchowdhury.hw05;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TrackDetailsActivity extends AppCompatActivity {

    static TrackInfo trackInfo;
    RequestParam rp;

    //checks for connection
    private boolean isCOnnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_details);
        //receives intent from the mainactivity
        Intent intent = getIntent();
        trackInfo = intent.getExtras().getParcelable("json");
       /* ImageView imageView = (ImageView) findViewById(R.id.imageView);
        TextView artist = (TextView) findViewById(R.id.textViewTrkArtist);
        TextView trackname = (TextView) findViewById(R.id.textViewTrkName);
        TextView url = (TextView) findViewById(R.id.textViewTrkURL);
        */
        if (isCOnnected()) {
            rp = new RequestParam("GET", "http://ws.audioscrobbler.com/2.0/");
            rp.addParam("method","track.getsimilar");
            rp.addParam("artist",trackInfo.getArtist());
            rp.addParam("track",trackInfo.getTrack_name());
            rp.addParam("api_key","bb069cb05fbbab336856e9dde94327e6");
            rp.addParam("limit","20");
            rp.addParam("format","json");
           // new DownloadJsonAsync(MainActivity.this,MainActivity.this).execute(rp);
        } else {
           // Toast.makeText(MainActivity.this, "No connection", Toast.LENGTH_SHORT).show();
        }








    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {   // what the item does on selection
        switch(item.getItemId()) {
            case R.id.action_home:
                Intent intent = new Intent(TrackDetailsActivity.this,MainActivity.class);
                startActivity(intent); // call activity mainactivity
                finish();         //finish this activity
                break;
            case R.id.action_quit:
                finishAffinity();  // to finish current activity and all parent activity
                System.exit(0);
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  // to add the options overflow in action bar
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.overflow, menu);
        return true;    }
}
