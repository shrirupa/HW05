package com.example.sgchowdhury.hw05;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchResults extends AppCompatActivity {
    static ArrayList<TrackInfo> trackInfoArrayList;

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

        ScrollView sv_main= (ScrollView) findViewById(R.id.svmain);
        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);

        for (final TrackInfo trackInfo: trackInfoArrayList){
            listItemUI item = new listItemUI(this);
            View itemView= (View)item;
            item.artist.setText(trackInfo.getArtist());
            item.trackname.setText(trackInfo.getTrack_name());
            Picasso.with(SearchResults.this).load(trackInfo.getImage_small()).into(item.imageView);

            container.addView(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i1 = new Intent (SearchResults.this, TrackDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("json",trackInfo);
                    i1.putExtras(bundle);

                }
            });
        }
        sv_main.addView(container);

    }
}
