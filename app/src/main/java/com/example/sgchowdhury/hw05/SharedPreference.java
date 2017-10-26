/***
 * Homework 05
 * Music App
 * Gana Ramesan, Shrirupa Chowdhury
 */
package com.example.sgchowdhury.hw05;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.gson.Gson;

public class SharedPreference {

    public static final String PREFS_NAME = "MUSIC_APP";
    public static final String FAVORITES = "Track_Favorite";
   // HashSet<TrackInfo> fv = new HashSet<>();
    public SharedPreference() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, List<TrackInfo> favorites) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.clear();
        editor.apply();
        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();


    }

    public void addFavorite(Context context, TrackInfo trackInfo) {
        ArrayList<TrackInfo> favorites = getFavorites(context);

        if (favorites == null) {
            favorites = new ArrayList<TrackInfo>();
        }
       // fv.add(trackInfo);
       // if (fv.contains(trackInfo))
        favorites.add(trackInfo);
        saveFavorites(context, favorites);
    }

    public void removeFavorite(Context context, TrackInfo product) {
        ArrayList<TrackInfo> favorites = getFavorites(context);
        if (favorites != null) {
            if(favorites.contains(product)) {
                favorites.remove(product);
                saveFavorites(context, favorites);
            }
        }
    }

    public ArrayList<TrackInfo> getFavorites(Context context) {
        SharedPreferences settings;
        List<TrackInfo> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            TrackInfo[] favoriteItems = gson.fromJson(jsonFavorites,
                    TrackInfo[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<TrackInfo>(favorites);
        } else
            return null;

        return (ArrayList<TrackInfo>) favorites;
    }
}