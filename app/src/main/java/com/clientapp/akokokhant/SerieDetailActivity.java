package com.clientapp.akokokhant;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class SerieDetailActivity extends AppCompatActivity {
static SeriesModel model;
static RecyclerView rcview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie_detail);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().build();
        ImageView myImage=findViewById(R.id.movieImage);
        TextView movieName=findViewById(R.id.movNme);
        Glide.with(getApplicationContext())
                .load(model.imglink)
                .into(myImage);
        movieName.setText(model.name);
        rcview=findViewById(R.id.rcview);
        Firebaseconnect.firebaseContext=getApplicationContext();
        Firebaseconnect.firebaseActivity=this;
        Firebaseconnect connect=new Firebaseconnect();
        connect.getEpisode(model.name);


    }
}
