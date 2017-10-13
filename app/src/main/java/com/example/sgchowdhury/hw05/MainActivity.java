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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RequestParam rp;

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
        //get the search button and edittext
        Button search = (Button) findViewById(R.id.buttonSearch);
        final EditText trackname = (EditText) findViewById(R.id.editTextTrackSearch);
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
                        rp.addParam("track",track);
                        rp.addParam("api_key","bb069cb05fbbab336856e9dde94327e6");
                        rp.addParam("limit","20");
                        rp.addParam("format","json");
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
