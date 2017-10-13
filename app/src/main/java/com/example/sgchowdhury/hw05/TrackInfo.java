package com.example.sgchowdhury.hw05;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sg chowdhury on 11-10-2017.
 */

public class TrackInfo implements Parcelable {
    String track_name,artist,url,image_small,image_large;

    public String getTrack_name() {
        return track_name;
    }

    public void setTrack_name(String track_name) {
        this.track_name = track_name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage_small() {
        return image_small;
    }

    public void setImage_small(String image_small) {
        this.image_small = image_small;
    }

    public String getImage_large() {
        return image_large;
    }

    public void setImage_large(String image_large) {
        this.image_large = image_large;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.track_name);
        dest.writeString(this.artist);
        dest.writeString(this.url);
        dest.writeString(this.image_small);
        dest.writeString(this.image_large);
    }

    public TrackInfo() {
    }

    protected TrackInfo(Parcel in) {
        this.track_name = in.readString();
        this.artist = in.readString();
        this.url = in.readString();
        this.image_small = in.readString();
        this.image_large = in.readString();
    }

    public static final Parcelable.Creator<TrackInfo> CREATOR = new Parcelable.Creator<TrackInfo>() {
        @Override
        public TrackInfo createFromParcel(Parcel source) {
            return new TrackInfo(source);
        }

        @Override
        public TrackInfo[] newArray(int size) {
            return new TrackInfo[size];
        }
    };
}
