package com.clientapp.akokokhant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class PlayVideoActivity extends AppCompatActivity {
public static String videourl;
SimpleExoPlayerView exoPlayerView;
SimpleExoPlayer player;
ProgressBar loading;
RewardedAd rewardedAd;
Handler handler=new Handler();
Runnable runnable=new Runnable() {
    @Override
    public void run() {
        if (rewardedAd.isLoaded())
        {
            RewardedAdCallback adCallback = new RewardedAdCallback() {
                @Override
                public void onRewardedAdOpened() {
                    // Ad opened.
                }

                @Override
                public void onRewardedAdClosed() {
                    // Ad closed.
                }

                @Override
                public void onUserEarnedReward(@NonNull RewardItem reward) {
                    // User earned reward.
                }

                @Override
                public void onRewardedAdFailedToShow(int errorCode) {
                    // Ad failed to display.
                }
            };
            rewardedAd.show(getParent(),adCallback);


        }
    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        MobileAds.initialize(getApplicationContext(),getResources().getString(R.string.ads_id));
        rewardedAd = new RewardedAd(this,getResources().getString(R.string.reward_id));


        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                // Ad successfully loaded.
            }

            @Override
            public void onRewardedAdFailedToLoad(int errorCode) {
                // Ad failed to load.
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);

    StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().build();
        StrictMode.setThreadPolicy(policy);
        exoPlayerView=findViewById(R.id.exoplayer);
        loading=findViewById(R.id.videoloading);
        player= ExoPlayerFactory.newSimpleInstance(getApplicationContext(),new DefaultTrackSelector());
        DefaultHttpDataSourceFactory dataSourceFactory=new DefaultHttpDataSourceFactory("exo player");
        ExtractorsFactory ef=new DefaultExtractorsFactory();
        Uri videouri1=Uri.parse(videourl);
        ExtractorMediaSource mediaSource=new ExtractorMediaSource(videouri1,dataSourceFactory,ef,null,null);
        player.prepare(mediaSource);
        player.setPlayWhenReady(true);
        exoPlayerView.setPlayer(player);
        SimpleExoPlayer.EventListener listener=new ExoPlayer.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if (playbackState==SimpleExoPlayer.STATE_BUFFERING){
                    loading.setVisibility(View.VISIBLE);
                }else{
                    loading.setVisibility(View.GONE);
                }
            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity() {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }
        };
        player.addListener(listener);
       /* handler.postDelayed(runnable,30000);*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        player.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.setPlayWhenReady(false);
    }
}

