package com.clientapp.akokokhant;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;

public class Firebaseconnect {
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    CollectionReference categoryRef=db.collection("categories");
    CollectionReference moviesRef=db.collection("movie");
    CollectionReference seriesRef=db.collection("series");
           public static Context firebaseContext;
          public static Activity firebaseActivity;

            public void getAllMovies(String name)
                    
            {
                moviesRef.whereEqualTo("category",name).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        ArrayList<MovieModel> seriesModels=new ArrayList<MovieModel>();
                        for (DocumentSnapshot snapshot:queryDocumentSnapshots){
                            seriesModels.add(snapshot.toObject(MovieModel.class));

                        }
                        MovieRecyclerAdapter adapter=new MovieRecyclerAdapter(seriesModels,firebaseContext,firebaseActivity);
                        MovieFragment.rcView.setAdapter(adapter);
                        GridLayoutManager gm=new GridLayoutManager(firebaseContext,3);
                        MovieFragment.rcView.setLayoutManager(gm);
                    }

                });
            }
    public void getAllSeries()

    {
        seriesRef.whereEqualTo("category","series").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                ArrayList<SeriesModel> seriesModels=new ArrayList<SeriesModel>();
                for (DocumentSnapshot snapshot:queryDocumentSnapshots){
                    seriesModels.add(snapshot.toObject(SeriesModel.class));
                    SeriesRecycleAdapter adapter=new SeriesRecycleAdapter(seriesModels,firebaseContext,firebaseActivity);
                    SerieFragment.rcView.setAdapter(adapter);
                    GridLayoutManager gm=new GridLayoutManager(firebaseContext,3);
                    SerieFragment.rcView.setLayoutManager(gm);
                }
            }

        });
    }
    public void getEpisode(String seriesname)
    {
        moviesRef.whereEqualTo("series",seriesname).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                ArrayList<MovieModel> movieModels=new ArrayList<MovieModel>();
                for( DocumentSnapshot snapshot : queryDocumentSnapshots)
                {
                    movieModels.add(snapshot.toObject(MovieModel.class));
                }
                EpisodeAdapter adapter=new EpisodeAdapter(movieModels,firebaseContext,firebaseActivity);
                SerieDetailActivity.rcview.setAdapter(adapter);
                LinearLayoutManager lm=new LinearLayoutManager(firebaseContext, RecyclerView.VERTICAL,false);
                SerieDetailActivity.rcview.setLayoutManager(lm);
            }
        });
    }

}
