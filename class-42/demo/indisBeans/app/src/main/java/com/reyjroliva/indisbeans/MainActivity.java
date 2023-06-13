package com.reyjroliva.indisbeans;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class MainActivity extends AppCompatActivity {
  private static String TAG = "MainActivity";
  private AdView myBannerAd;
  private InterstitialAd myInterstitialAd;
  private RewardedAd myRewardedAd;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    MobileAds.initialize(this, new OnInitializationCompleteListener() {
      @Override
      public void onInitializationComplete(InitializationStatus initializationStatus) {
      }
    });

    setUpBannerAd();
    setUpInterstitialAd();
    setUpRewardedAd();
  }

  private void setUpBannerAd() {
    myBannerAd = findViewById(R.id.bannerAdView);
    AdRequest adRequest = new AdRequest.Builder().build();
    myBannerAd.loadAd(adRequest);
  }

  private void setUpInterstitialAd() {
    AdRequest interstitialAdRequest = new AdRequest.Builder().build();

    InterstitialAd.load(this, "ca-app-pub-3940256099942544/1033173712", interstitialAdRequest, new InterstitialAdLoadCallback() {
      @Override
      public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
        super.onAdFailedToLoad(loadAdError);
        // Handle the error
        Log.d(TAG, loadAdError.toString());
        myInterstitialAd = null;
      }

      @Override
      public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
        super.onAdLoaded(interstitialAd);
        // the myInterstitialAd reference will be null until an ad is loaded
        // once we load an ad, set the myInterstitialAd reference to that ad
        myInterstitialAd = interstitialAd;

        myInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
          @Override
          public void onAdClicked() {
            super.onAdClicked();
            // Called when an ad is clicked on
            Log.d(TAG, "Ad was clicked");
          }

          @Override
          public void onAdDismissedFullScreenContent() {
            super.onAdDismissedFullScreenContent();
            // Called when ad is dismissed
            // Set the ad reference to null so you don;t show the ad a second time
            myInterstitialAd = null;
          }

          @Override
          public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
            super.onAdFailedToShowFullScreenContent(adError);
            // Called when the ad fails to show
            Log.e(TAG, "Ad failed to show fullscreen content");
            myInterstitialAd = null;
          }

          @Override
          public void onAdImpression() {
            super.onAdImpression();
            // Called when an impression is recorded for an ad
            Log.d(TAG, "Ad recorded an impression");
          }

          @Override
          public void onAdShowedFullScreenContent() {
            super.onAdShowedFullScreenContent();
            // Called when the ad is shown in full
            Log.d(TAG, "Ad showed fullscreen content");
          }
        });
      }
    });

    Button interstitialAdButton = findViewById(R.id.interstitialAdButton);
    interstitialAdButton.setOnClickListener(v -> {
      if(myInterstitialAd != null) {
        myInterstitialAd.show(MainActivity.this);
      } else {
        Log.d(TAG, "The interstitial ad wasn't ready yet");
      }
    });
  }

  private void setUpRewardedAd() {
    AdRequest rewardedAdRequest = new AdRequest.Builder().build();

    RewardedAd.load(this, "ca-app-pub-3940256099942544/5224354917", rewardedAdRequest, new RewardedAdLoadCallback() {
      @Override
      public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
        super.onAdFailedToLoad(loadAdError);
        // Handle the error
        Log.d(TAG, loadAdError.toString());
        myRewardedAd = null;
      }

      @Override
      public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
        super.onAdLoaded(rewardedAd);
        myRewardedAd = rewardedAd;
        Log.d(TAG, "Ad was loaded");
      }
    });

    Button myRewardedAdButton = findViewById(R.id.rewardedAdButton);
    myRewardedAdButton.setOnClickListener(v -> {
      Activity activityContext = MainActivity.this;

      if (myRewardedAd != null) {
        // Before you show your ad, make sure you have set the fullscreen callback
        myRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
          @Override
          public void onAdClicked() {
            super.onAdClicked();
            // Called when a click is recorded for an ad.
            Log.d(TAG, "Ad was clicked.");
          }

          @Override
          public void onAdDismissedFullScreenContent() {
            super.onAdDismissedFullScreenContent();
            // Called when ad is dismissed.
            // Set the ad reference to null so you don't show the ad a second time.
            Log.d(TAG, "Ad dismissed fullscreen content.");
            myRewardedAd = null;
          }

          @Override
          public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
            super.onAdFailedToShowFullScreenContent(adError);
            // Called when ad fails to show.
            Log.e(TAG, "Ad failed to show fullscreen content.");
            myRewardedAd = null;
          }

          @Override
          public void onAdImpression() {
            super.onAdImpression();
            // Called when an impression is recorded for an ad.
            Log.d(TAG, "Ad recorded an impression.");
          }

          @Override
          public void onAdShowedFullScreenContent() {
            super.onAdShowedFullScreenContent();
            // Called when ad is shown.
            Log.d(TAG, "Ad showed fullscreen content.");
          }
        });

        // Show the ad
        myRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
          @Override
          public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
            // Handle the reward
            int rewardAmount = rewardItem.getAmount();
            String rewardType = rewardItem.getType();
            Log.d(TAG, "The user earned the reward: Amount is: " + rewardAmount + " and type is: " + rewardType);
          }
        });
      } else {
        Log.d(TAG, "The rewarded ad wasn't loaded yet.");
      }
    });
  }
}
