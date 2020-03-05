package com.clientapp.akokokhant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class SeriesRecycleAdapter extends RecyclerView.Adapter<SeriesRecycleAdapter.SeriesHolder> {
    private InterstitialAd mInterstitialAd;
    ArrayList<SeriesModel> seriesModels=new ArrayList<>();
    Context context;
    Activity Activity;

    public SeriesRecycleAdapter(ArrayList<SeriesModel> seriesModels, Context context, android.app.Activity activity) {
        this.seriesModels = seriesModels;
        this.context = context;
        this.Activity = activity;
    }

    @NonNull
    @Override
    public SeriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myLayoutinflater=LayoutInflater.from(parent.getContext());
        View myView=myLayoutinflater.inflate(R.layout.seriesitem,parent,false);
        SeriesHolder holder=new SeriesHolder(myView);
        MobileAds.initialize(context,context.getResources().getString(R.string.ads_id));
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(context.getResources().getString(R.string.int_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        return holder;

    }



    @Override
    public void onBindViewHolder(@NonNull SeriesRecycleAdapter.SeriesHolder holder, final int position) {
        Glide.with(context)
                .load(seriesModels.get(position).imglink)
                .into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdLoaded() {
                            // Code to be executed when an ad finishes loading.
                        }

                        @Override
                        public void onAdFailedToLoad(int errorCode) {
                            // Code to be executed when an ad request fails.
                            SerieDetailActivity.model=seriesModels.get(position);
                            Intent myIntent=new Intent(context,SerieDetailActivity.class);
                            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(myIntent);
                        }

                        @Override
                        public void onAdOpened() {
                            // Code to be executed when the ad is displayed.
                        }

                        @Override
                        public void onAdClicked() {
                            // Code to be executed when the user clicks on an ad.
                            SerieDetailActivity.model=seriesModels.get(position);
                            Intent myIntent=new Intent(context,SerieDetailActivity.class);
                            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(myIntent);
                        }

                        @Override
                        public void onAdLeftApplication() {
                            // Code to be executed when the user has left the app.
                        }

                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            SerieDetailActivity.model=seriesModels.get(position);
                            Intent myIntent=new Intent(context,SerieDetailActivity.class);
                            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(myIntent);
                        }
                    });
                }
                else {
                    SerieDetailActivity.model=seriesModels.get(position);
                    Intent myIntent=new Intent(context,SerieDetailActivity.class);
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(myIntent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return seriesModels.size();
    }
    public class SeriesHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public SeriesHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imgserie);
        }
    }
}
