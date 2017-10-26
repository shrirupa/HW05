/***
 * Homework 05
 * Music App
 * Gana Ramesan, Shrirupa Chowdhury
 */

package com.example.sgchowdhury.hw05;

import android.app.Activity;
import android.content.Context;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;


public class CustomAdapter extends ArrayAdapter<TrackInfo> {

    List<TrackInfo> mData;
    Context mContext;
    SharedPreference sharedPreference;

    public CustomAdapter(@NonNull Context context, @NonNull List<TrackInfo> trackInfoList) {
        super(context, R.layout.tracklist, trackInfoList);
        this.mContext = context;
        this.mData = trackInfoList;
        sharedPreference = new SharedPreference();

    }

    private class ViewHolder {
        TextView trackname;
        TextView artistname;
        ImageView trackImg;
        ImageView favoriteImg;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public TrackInfo getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /*Checks whether a particular product exists in SharedPreferences*/
    public boolean checkFavoriteItem(TrackInfo checkProduct) {
        boolean check = false;
        List<TrackInfo> favorites = sharedPreference.getFavorites(mContext);
        if (favorites != null) {
            for (TrackInfo trackInfo : favorites) {
                if (trackInfo.equals(checkProduct)) {
                    check = true;
                    Log.d("found", check + "");
                    break;
                }
            }
        }
        return check;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.tracklist, null);
            holder = new ViewHolder();
            holder.trackname = (TextView) convertView
                    .findViewById(R.id.tvTrackName);
            holder.artistname = (TextView) convertView
                    .findViewById(R.id.tvArtistName);
            holder.trackImg = (ImageView) convertView
                    .findViewById(R.id.imgTrkSmall);
            holder.favoriteImg = (ImageView) convertView
                    .findViewById(R.id.ButtonFav);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TrackInfo trackInfo = (TrackInfo) getItem(position);
        holder.artistname.setText(trackInfo.getArtist());
        holder.trackname.setText(trackInfo.getTrack_name());
        if(!trackInfo.getImage_small().isEmpty())
        Picasso.with(mContext).load(trackInfo.getImage_small()).into(holder.trackImg);
        //holder.trackImg.set



        /*If a product exists in shared preferences then set heart_red drawable and set a tag*/
        if (checkFavoriteItem(trackInfo)) {
            holder.favoriteImg.setImageResource(R.drawable.on);
            holder.favoriteImg.setTag("on");
        } else {
            holder.favoriteImg.setImageResource(R.drawable.off);
            holder.favoriteImg.setTag("off");
        }


        View view = convertView;
        final int pt = position;
        convertView.findViewById(R.id.ButtonFav).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageView imageView = (ImageView) view.findViewById(R.id.ButtonFav);

                Log.d("testing", "onClick: " + imageView.getTag());

                if (imageView.getTag().equals("off")) {
                    Log.d("shir", mData.size() + "");
                    if (sharedPreference.getFavorites(mContext)!=null)
                    { if (sharedPreference.getFavorites(mContext).size() < 20) {
                        imageView.setImageResource(R.drawable.on);
                        imageView.setTag("on");
                        MainActivity.sp.addFavorite(getContext(), mData.get(pt));
                        Toast.makeText(mContext, "Added to Favourite", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    } else {
                        Toast.makeText(mContext, "Favourite List is Full!", Toast.LENGTH_SHORT).show();
                    }
                }else{

                        imageView.setImageResource(R.drawable.on);
                        imageView.setTag("on");
                        MainActivity.sp.addFavorite(getContext(), mData.get(pt));
                        Toast.makeText(mContext, "Added to Favourite", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();




                    }

                } else {
                    imageView.setImageResource(R.drawable.off);
                    imageView.setTag("off");
                    Log.d("Track", "if on: track " + pt);
                    MainActivity.sp.removeFavorite(getContext(), mData.get(pt));
                    if (mContext.getClass().getName().equals("com.example.sgchowdhury.hw05.MainActivity"))
                        mData = sharedPreference.getFavorites(mContext);
                    Toast.makeText(mContext, "Removed from Favourite", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();

                }
            }
        });

        return convertView;
    }


}
