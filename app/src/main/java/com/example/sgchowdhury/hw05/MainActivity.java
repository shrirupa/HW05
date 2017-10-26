/***
 * Homework 05
 * Music App
 * Gana Ramesan, Shrirupa Chowdhury
 */

package com.example.sgchowdhury.hw05;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RequestParam rp;
    EditText trackname;
    static SharedPreference sp = new SharedPreference();
    ArrayList<TrackInfo> trkarray;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {   // what the item does on selection
        switch(item.getItemId()) {
            case R.id.action_home:
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
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getSharedPreferences("MUSIC_APP", 0).edit().clear().commit(); // ----- to clear all shared preferences

        //get the search button and edittext
        Log.d("demo", "onCreate: home");
        Button search = (Button) findViewById(R.id.buttonSearch);
        trackname = (EditText) findViewById(R.id.editTextTrackSearch);
        //on click of search button
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String track = trackname.getText().toString();
                if(trackname.length()!=0)
                {
                    if (isCOnnected()) {

                        rp = new RequestParam("GET", "http://ws.audioscrobbler.com/2.0/");
                        rp.addParam("method","track.search");
                        rp.addParam("track",trackname.getText().toString());
                        rp.addParam("api_key","bb069cb05fbbab336856e9dde94327e6");
                        rp.addParam("limit","20");
                        rp.addParam("format","json");
                        Log.d("demo", "onClick: track" + trackname.getText().toString());
                        try {
                            Log.d("demo", "onClick: rp" + rp.Encodedurl());
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        new DownloadJsonAsync(MainActivity.this,MainActivity.this).execute(rp);
                    } else {
                        Toast.makeText(MainActivity.this, "No connection", Toast.LENGTH_SHORT).show();
                    }
                }
                else{

                Toast.makeText(MainActivity.this, "No track name entered!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if(sp.getFavorites(MainActivity.this)!=null) {
             trkarray = sp.getFavorites(MainActivity.this);
            if (trkarray.size() != 0) {
                ListView listView = (ListView) findViewById(R.id.tracklistFavorite);
                CustomAdapter customAdapter = new CustomAdapter(this, trkarray);
                listView.setAdapter(customAdapter);
                customAdapter.setNotifyOnChange(true);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Log.d("demo", "onItemClick: ");
                        Intent i1 = new Intent (MainActivity.this, TrackDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("json",trkarray.get(i));
                        i1.putExtras(bundle);
                        startActivity(i1);

                    }
                });
            }
        }
    }
    //checks for connection
    private boolean isCOnnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }


}
