package com.clientapp.akokokhant;


import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


/**
 * A simple {@link Fragment} subclass.
 */
public class SerieFragment extends Fragment {

static RecyclerView rcView;
    public SerieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView= inflater.inflate(R.layout.fragment_serie, container, false);
        MobileAds.initialize(getContext(),getResources().getString(R.string.banenr_id));
        AdView mAdView = myView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().build();
        rcView=myView.findViewById(R.id.rcviewseries);
        Firebaseconnect.firebaseContext=getContext();
        Firebaseconnect.firebaseActivity=getActivity();
        Firebaseconnect connect=new Firebaseconnect();
        connect.getAllSeries();
        return myView;

    }

}
