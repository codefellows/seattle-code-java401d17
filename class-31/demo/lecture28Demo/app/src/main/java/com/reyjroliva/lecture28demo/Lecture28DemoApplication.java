package com.reyjroliva.lecture28demo;

import android.app.Application;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.analytics.pinpoint.AWSPinpointAnalyticsPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.predictions.aws.AWSPredictionsPlugin;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

public class Lecture28DemoApplication extends Application {
  public static final String TAG = "lecture28demoapplication";

  @Override
  public void onCreate() {
    super.onCreate();

    try {
      Amplify.addPlugin(new AWSApiPlugin());
      Amplify.addPlugin(new AWSCognitoAuthPlugin());
      Amplify.addPlugin(new AWSPredictionsPlugin());
      Amplify.addPlugin(new AWSPinpointAnalyticsPlugin());
      Amplify.addPlugin(new AWSS3StoragePlugin());
      Amplify.configure(getApplicationContext());
      Log.i(TAG, "Initialized Amplify");
    } catch (AmplifyException ae) {
      Log.e(TAG, "Error initializing Amplify: " + ae.getMessage(), ae);
    }
  }
}
