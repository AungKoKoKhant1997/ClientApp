package com.clientapp.akokokhant;


import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    String name;
    static RecyclerView rcView;
    public MovieFragment() {
        // Required empty public constructor
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(mInterstitialAd.isLoaded())
            {
                mInterstitialAd.show();;
            }
        }
    };
    public InterstitialAd mInterstitialAd;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView =inflater.inflate(R.layout.fragment_movie, container, false);
        MobileAds.initialize(getContext(),getResources().getString(R.string.banenr_id));
        AdView mAdView = myView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().build();
        rcView=myView.findViewById(R.id.rcview);
        Firebaseconnect.firebaseContext=getContext();
        Firebaseconnect.firebaseActivity=getActivity();
        Firebaseconnect connect=new Firebaseconnect();
        connect.getAllMovies(name);
        MobileAds.initialize(getContext(),getResources().getString(R.string.ads_id));
        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.int_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        for(int i=1;i<=20;i++) {
            handler.postDelayed(runnable, 10000 * i);
        }
        return myView;
    }
}
