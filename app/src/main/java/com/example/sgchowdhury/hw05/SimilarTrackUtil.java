/***
 * Homework 05
 * Music App
 * Gana Ramesan, Shrirupa Chowdhury
 */
package com.example.sgchowdhury.hw05;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SimilarTrackUtil {
    static ArrayList<TrackInfo> parseTrackInfos(String in) throws JSONException {
        ArrayList<TrackInfo> trackInfoArrayList = new ArrayList<>();
        JSONObject root = new JSONObject(in);
        Log.d("root",root+"");
        JSONArray tracks = root.getJSONObject("similartracks").getJSONArray("track");
        Log.d("tracks",tracks+"");
        for(int i = 0;i<tracks.length();i++){
            JSONObject tracksJSONObject = tracks.getJSONObject(i);
            TrackInfo trackInfo = new TrackInfo();
            trackInfo.setTrack_name(tracksJSONObject.getString("name"));
            trackInfo.setUrl(tracksJSONObject.getString("url"));
            trackInfo.setArtist(tracksJSONObject.getJSONObject("artist").getString("name"));
            JSONArray imagesArray = tracksJSONObject.getJSONArray("image");
            trackInfo.setImage_small(imagesArray.getJSONObject(0).getString("#text"));
            trackInfo.setImage_large(imagesArray.getJSONObject(2).getString("#text"));
            trackInfoArrayList.add(trackInfo);
        }
        return trackInfoArrayList;
    }
}
