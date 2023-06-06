package com.reyjroliva.lecture28demo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.auth.cognito.result.AWSCognitoAuthSignOutResult;
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.core.Amplify;
import com.google.android.material.snackbar.Snackbar;
import com.reyjroliva.lecture28demo.R;

public class UserProfileActivity extends AppCompatActivity {
  public static final String TAG = "UserProfileAcivity";
  public static final String USER_NICKNAME_TAG = "userNickname"; // top of class, so other classes can use it -- DON'T DECLARE THIS ELSEWHERE

  // Class-36 Follow up: Added to get the authUser for determining which buttons to display
  AuthUser authUser;

  SharedPreferences preferences; // at top of class so it can live long enough to be used in a click handler!

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_profile);

    preferences = PreferenceManager.getDefaultSharedPreferences(this);

    populateNicknameEditText(preferences);
    setUpSaveButton(preferences);
    setUpLoginButton();
    setUpLogoutButton();
  }

  @Override
  protected void onResume() {
    super.onResume();

    // Class-36 Follow up: The call below kicks off the check for an auth user used to determine which buttons to display
    checkForAuthUser();
  }

  // Class-36 Follow up: logic in the following 2 methods determines whether or not there is an authenticated user logged in
  // the login button is shown if there is no logged in user
  // if there is a logged in user, the logout button is rendered
  // NOTE: on logout, the user will need to leave the user settings page and return before the buttons will be properly re-rendered
  // if you'd like to circumvent this, consider adding an intent to to move to a new activity on a successful logout
  public void checkForAuthUser() {
    Amplify.Auth.getCurrentUser(
      success -> {
        Log.i(TAG, "User authenticated with username: " + success.getUsername());
        authUser = success;
        runOnUiThread(this::renderButtons);
      },
      failure -> {
        Log.i(TAG, "There is no current authenticated user");
        authUser = null;
        runOnUiThread(this::renderButtons);
      }
    );
  }

  public void renderButtons() {
    if (authUser == null) {
      Button loginButton = findViewById(R.id.userProfileActivityLoginButton);
      loginButton.setVisibility(View.VISIBLE);
      Button logoutButton = findViewById(R.id.userProfileActivityLogoutButton);
      logoutButton.setVisibility(View.INVISIBLE);
    } else {
      Button loginButton = findViewById(R.id.userProfileActivityLoginButton);
      loginButton.setVisibility(View.INVISIBLE);
      Button logoutButton = findViewById(R.id.userProfileActivityLogoutButton);
      logoutButton.setVisibility(View.VISIBLE);
    }
  }

  public void populateNicknameEditText(SharedPreferences preferences) {
    // grab value from sharedPreferences and set out EditText with that value
    String userNickname = preferences.getString(USER_NICKNAME_TAG, "");
    ((EditText) findViewById(R.id.userProfileActivityNicknameInputEditText)).setText(userNickname);
  }

  public void setUpSaveButton(SharedPreferences preferences) {
    Button saveButton = findViewById(R.id.userProfileActivitySaveButton);
    saveButton.setOnClickListener(v -> {
      // creating an editor because SharedPreferences is read-only
      SharedPreferences.Editor preferenceEditor = preferences.edit(); // this fails if you declare "preferences" within the onCreate()

      // grabbing the string we want to save from the user input
      EditText userNicknameEditText = findViewById(R.id.userProfileActivityNicknameInputEditText);
      String userNicknameString = userNicknameEditText.getText().toString();

      // save the string to shared preferences
      preferenceEditor.putString(USER_NICKNAME_TAG, userNicknameString);
      preferenceEditor.apply(); // Nothing will happen if you don't do this!

      Snackbar.make(findViewById(R.id.userProfileActivity), "Settings saved!", Snackbar.LENGTH_SHORT).show();
      Toast.makeText(UserProfileActivity.this, "Settings saved!", Toast.LENGTH_SHORT).show();
    });
  }

  public void setUpLoginButton() {
    Button loginButton = findViewById(R.id.userProfileActivityLoginButton);
    loginButton.setOnClickListener(v -> {
      Intent goToLoginActivity = new Intent(UserProfileActivity.this, LoginActivity.class);
      startActivity(goToLoginActivity);
    });
  }

  public void setUpLogoutButton() {
    Button logoutButton = findViewById(R.id.userProfileActivityLogoutButton);
    logoutButton.setOnClickListener(v -> {
      // Amplify User Logout code block
      AuthSignOutOptions options = AuthSignOutOptions.builder()
        .globalSignOut(true)
        .build();

      Amplify.Auth.signOut(options, signOutResult -> {
        if (signOutResult instanceof AWSCognitoAuthSignOutResult.CompleteSignOut) {
          Log.i(TAG,"Global logout successful!");
        } else if (signOutResult instanceof AWSCognitoAuthSignOutResult.PartialSignOut) {
          Log.i(TAG,"Partial logout successful!");
        } else if (signOutResult instanceof AWSCognitoAuthSignOutResult.FailedSignOut) {
          Log.i(TAG,"Logout failed: " + signOutResult.toString());
        }
      });
    });
  }
}
