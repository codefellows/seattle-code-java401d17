package com.reyjroliva.lecture28demo;

import android.app.Application;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;

public class Lecture28DemoApplication extends Application {
  public static final String TAG = "lecture28demoapplication";

  @Override
  public void onCreate() {
    super.onCreate();

    try {
      Amplify.addPlugin(new AWSApiPlugin());
      Amplify.addPlugin(new AWSCognitoAuthPlugin());
      Amplify.configure(getApplicationContext());
      Log.i(TAG, "Initialized Amplify");
    } catch (AmplifyException ae) {
      Log.e(TAG, "Error initializing Amplify: " + ae.getMessage(), ae);
    }
  }
}
