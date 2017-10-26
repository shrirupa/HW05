/***
 * Homework 05
 * Music App
 * Gana Ramesan, Shrirupa Chowdhury
 */

package com.example.sgchowdhury.hw05;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.support.v4.app.FragmentTransaction;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchResults extends AppCompatActivity {
    static ArrayList<TrackInfo> trackInfoArrayList;
    static int currtrck;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {   // what the item does on selection
        switch(item.getItemId()) {
            case R.id.action_home:
                Intent intent = new Intent(SearchResults.this,MainActivity.class);
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        //receives intent from the mainactivity
        Intent intent = getIntent();
        trackInfoArrayList = intent.getExtras().getParcelableArrayList("json");
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

        ListView listView = (ListView) findViewById(R.id.tracklist);
        CustomAdapter customAdapter = new CustomAdapter(this,trackInfoArrayList);
        listView.setAdapter(customAdapter);
        customAdapter.setNotifyOnChange(true);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("demo", "onItemClick: ");
                Intent i1 = new Intent (SearchResults.this, TrackDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("json",trackInfoArrayList.get(i));
                i1.putExtras(bundle);
                startActivity(i1);
                currtrck = i;


            }
        });


    }









}
