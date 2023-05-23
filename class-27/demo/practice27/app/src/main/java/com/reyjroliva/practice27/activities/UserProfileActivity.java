package com.reyjroliva.practice27.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.reyjroliva.practice27.R;

public class UserProfileActivity extends AppCompatActivity {
  public static final String USER_NICKNAME_TAG = "userNickname"; // at the top of the class, so other classes can use it -- don't declare this anywhere else!!!

  SharedPreferences preferences; // at the top of the class, so it can live long enough to be used in a click handler!

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_profile);

    preferences = PreferenceManager.getDefaultSharedPreferences(this);

    // grab value from shared preferences and set our EditText value
    String userNickname = preferences.getString(USER_NICKNAME_TAG, "");
    ((EditText) findViewById(R.id.userProfileActivityNicknameInputEditText)).setText(userNickname);

    setUpSaveButton(preferences);
  }

  public void setUpSaveButton(SharedPreferences preferences) {
    Button saveButton = findViewById(R.id.userProfileActivitySaveButton);
    saveButton.setOnClickListener(v -> {
      SharedPreferences.Editor preferenceEditor = preferences.edit(); //this fails if you declare "preferences" variable in onCreate(), rather than just initializing it here
      EditText userNicknameEditText = (EditText) findViewById(R.id.userProfileActivityNicknameInputEditText);
      String userNicknameString = userNicknameEditText.getText().toString();

      preferenceEditor.putString(USER_NICKNAME_TAG, userNicknameString);
      preferenceEditor.apply(); // Nothing will save if you don't do this, so don't forget it!

      Snackbar.make(findViewById(R.id.userProfileActivity), "Settings saved!", Snackbar.LENGTH_SHORT).show();
      Toast.makeText(UserProfileActivity.this, "Settings saved!", Toast.LENGTH_SHORT).show();
    });
  }
}
