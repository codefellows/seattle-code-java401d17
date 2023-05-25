package com.reyjroliva.lecture28demo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.reyjroliva.lecture28demo.R;

public class UserProfileActivity extends AppCompatActivity {
  public static final String USER_NICKNAME_TAG = "userNickname"; // top of class, so other classes can use it -- DON'T DECLARE THIS ELSEWHERE

  SharedPreferences preferences; // at top of class so it can live long enough to be used in a click handler!

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_profile);

    preferences = PreferenceManager.getDefaultSharedPreferences(this);

    populateNicknameEditText(preferences);
    setUpSaveButton(preferences);
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
}
