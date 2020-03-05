package com.clientapp.akokokhant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

public class EpisodeAdapter  extends RecyclerView.Adapter<EpisodeAdapter.EpisodeHolder> {


    ArrayList<MovieModel> movieModels=new ArrayList<>();
    Context context;
    Activity myActivity;

    public EpisodeAdapter(ArrayList<MovieModel> movieModels, Context context, Activity myActivity) {
        this.movieModels = movieModels;
        this.context = context;
        this.myActivity = myActivity;
    }

    @NonNull
    @Override
    public EpisodeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myInfalter=LayoutInflater.from(parent.getContext());// getLayoutn
        View myView=myInfalter.inflate(R.layout.episodelist,parent,false);

        EpisodeHolder holder=new EpisodeHolder(myView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeHolder holder, final int position) {
        holder.sr.setText(position+1+"");
        holder.name.setText(movieModels.get(position).name);
        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,PlayVideoActivity.class);
                    try {
                        PlayVideoActivity.videourl = MediafireConnect.getVideoFileLink(movieModels.get(position).videolink);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    intent.setFlags((Intent.FLAG_ACTIVITY_NEW_TASK));
                    context.startActivity(intent);
                }

        });
    }

    @Override
    public int getItemCount() {
        return movieModels.size();
    }

    public class EpisodeHolder extends RecyclerView.ViewHolder{
        ImageView play;
        TextView sr,name;
        public EpisodeHolder(@NonNull View itemView) {
            super(itemView);
            play=itemView.findViewById(R.id.play);
            sr=itemView.findViewById(R.id.sr);
            name=itemView.findViewById(R.id.name);
        }
    }
}



