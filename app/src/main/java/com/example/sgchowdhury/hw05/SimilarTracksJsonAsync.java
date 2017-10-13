package com.example.sgchowdhury.hw05;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by sg chowdhury on 12-10-2017.
 */

public class SimilarTracksJsonAsync extends AsyncTask<RequestParam, Integer, String> {
    static StringBuilder sb = new StringBuilder();
    private Context context;
    private Activity activity;
    @Override
    protected String doInBackground(RequestParam... strings) {
        BufferedReader reader = null;
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
    }

    public SimilarTracksJsonAsync(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }
}
