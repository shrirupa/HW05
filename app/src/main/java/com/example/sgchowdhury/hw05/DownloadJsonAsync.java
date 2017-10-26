/***
 * Homework 05
 * Music App
 * Gana Ramesan, Shrirupa Chowdhury
 */

package com.example.sgchowdhury.hw05;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;


public class DownloadJsonAsync extends AsyncTask<RequestParam, Integer, String> {

    private Context context;
    private Activity activity;

    @Override
    protected String doInBackground(RequestParam... strings) {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {

            HttpURLConnection con = strings[0].setUpCOnnectio();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d("json",s);
        ArrayList<TrackInfo> trackInfoArrayList = new ArrayList<>();
        try {
            trackInfoArrayList = TrackInfoUtil.parseTrackInfos(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent i1 = new Intent (context, SearchResults.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("json",trackInfoArrayList);
        i1.putExtras(bundle);
        context.startActivity(i1);
    }

    public DownloadJsonAsync(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;

    }

}
