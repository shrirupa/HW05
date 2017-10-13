package com.example.sgchowdhury.hw05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by sg chowdhury on 12-10-2017.
 */

public class listItemUI extends LinearLayout {
    public ImageView imageView;
    public TextView trackname,artist;
    public ImageButton imageButton;

    public listItemUI(Context context) {
        super(context);
        inflateXML(context);
    }

    private void inflateXML(Context context) {
        LayoutInflater inflater= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView= inflater.inflate(R.layout.tracklist, this);
        this.artist = findViewById(R.id.tvArtistName);
        this.trackname = findViewById(R.id.tvTrackName);
        this.imageButton = findViewById(R.id.ButtonFav);
        this.imageView  = findViewById(R.id.imgTrkSmall);
    }


}
