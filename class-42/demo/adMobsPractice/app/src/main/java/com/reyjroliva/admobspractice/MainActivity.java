package com.reyjroliva.admobspractice;

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
  private AdView myAdView;
  private InterstitialAd myInterstitialAd;
  private AdRequest rewardedAdRequest = new AdRequest.Builder().build(); // make sure this one goes at the top of the class!!
  private RewardedAd myRewardedAd;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    MobileAds.initialize(this, new OnInitializationCompleteListener() {
      @Override
      public void onInitializationComplete(InitializationStatus initializationStatus) {
        // Not doing anything in here, but just to show you if you want to use this
      }
    });

    setUpBannerAd();
    setUpInterstitialAd();
    setUpRewardedAds();
  }

  private void setUpBannerAd() {
    // **********BANNER AD EXAMPLE**********
    myAdView = findViewById(R.id.adView);
    AdRequest adRequest = new AdRequest.Builder().build();
    myAdView.loadAd(adRequest);
  }

  private void setUpInterstitialAd() {
    // **********INTERSTITIAL AD EXAMPLE**********
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
        myInterstitialAd = interstitialAd;

        // Not required, but the code in here can be used to go to another activity on ad close
        myInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
          @Override
          public void onAdClicked() {
            // Called when a click is recorded for an ad.
            Log.d(TAG, "Ad was clicked.");
          }

          @Override
          public void onAdDismissedFullScreenContent() {
            // Called when ad is dismissed.
            // Set the ad reference to null so you don't show the ad a second time.
            Log.d(TAG, "Ad dismissed fullscreen content.");
            myInterstitialAd = null;
          }

          @Override
          public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
            // Called when ad fails to show.
            Log.e(TAG, "Ad failed to show fullscreen content.");
            myInterstitialAd = null;
          }

          @Override
          public void onAdImpression() {
            // Called when an impression is recorded for an ad.
            Log.d(TAG, "Ad recorded an impression.");
          }

          @Override
          public void onAdShowedFullScreenContent() {
            // Called when ad is shown.
            Log.d(TAG, "Ad showed fullscreen content.");
          }
        });

        Log.i(TAG, "onAdLoaded");
      }
    });

    // NOT FROM TUTORIAL: added to trigger interstitial via button
    Button interstitialButton = findViewById(R.id.interstitialButton);
    interstitialButton.setOnClickListener(v -> {
      if(myInterstitialAd != null) {
        myInterstitialAd.show(MainActivity.this);
      } else {
        Log.d(TAG, "The interstitial ad wasn't ready yet");
      }
    });
  }

  private void setUpRewardedAds() {
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

    Button rewardButton = findViewById(R.id.rewardedButton);
    rewardButton.setOnClickListener(v -> {
      if(myRewardedAd != null) {
        Activity activityContext = MainActivity.this;

        // Before you show your ad, make sure you set the callback
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

        // Show the rewarded ad
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
        Log.d(TAG, "The rewarded ad wasn't loaded yet");
      }
    });
  }
}
