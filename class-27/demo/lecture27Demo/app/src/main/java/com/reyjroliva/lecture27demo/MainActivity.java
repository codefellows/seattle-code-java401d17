package com.reyjroliva.lecture27demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.reyjroliva.lecture27demo.activities.OrderFormActivity;
import com.reyjroliva.lecture27demo.activities.UserProfileActivity;

public class MainActivity extends AppCompatActivity {
  public static final String PRODUCT_NAME_EXTRA_TAG = "productName"; // at top of class, for other classes to be able to reference

  private final String TAG = "MainActivity";

  SharedPreferences preferences;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    setUpSettingsButton();
    setUpSubmitButton();
    setUpOrderFormButton();
  }

  @Override
  protected void onResume() {
    super.onResume();

    preferences = PreferenceManager.getDefaultSharedPreferences(this);
    String userNickname = preferences.getString(UserProfileActivity.USER_NICKNAME_TAG, "No nickname");
    ((TextView) findViewById(R.id.mainActivityNicknameTextView)).setText(userNickname);
  }

  public void setUpSettingsButton() {
    ((ImageView) findViewById(R.id.mainActivitySettingsImageView)).setOnClickListener(v -> {
      Intent goToUserProfileIntent = new Intent(MainActivity.this, UserProfileActivity.class);
      startActivity(goToUserProfileIntent);
    });
  }

  public void setUpSubmitButton() {
    // Steps for adding functionality to JS UI elements
    // 1: Get UI element by ID
    // 2: Add event listener to that element
    // 3: Attach a callback function with an onClick() method
    // 4: Do stuff in the callback (onClick())

    // Adding functionality to a UI element in Java (Android)
    // Step 1: grab element using findByID()
    // grabbing a view, assigning to a variable, and setting the value in 2 lines
    Button submitButton = (Button) findViewById(R.id.mainActivitySubmitButton);
    // submitButton.setText("button"); // <-- "button" cannot be translated
    // grabbing a view and setting its values in one line (using string resources)
    ((Button) findViewById(R.id.mainActivitySubmitButton)).setText(R.string.main_activity_submit_button);

    // Step 2/3: add onClickListener
    submitButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // Step 4: Do stuff in the callback (onClick())
        System.out.println("Submit button pressed!");

        // A better way to log
        Log.v(TAG, "I'm a VERBOSE log");
        Log.d(TAG, "I'm a DEBUG log");
        Log.i(TAG, "I'm an INFO log");
        Log.w(TAG, "I'm  a WARNING log");
        Log.e(TAG, "I'm an ERROR log");
        Log.wtf(TAG, "WHAT A TERRIBLE FAILURE");

        /*
         Logging an exception
         Log.e(TAG, "insert your exception message here", new RuntimeException());
        try {
          // try some stuff
        } catch (RuntimeException re) {
          Log.e(TAG, "we caught a runtime exception", re);
        }
        */

        ((TextView) findViewById(R.id.mainActivitySubmittedTextView)).setText(R.string.submitted_confirmation);
      }
    });
  }

  public void setUpOrderFormButton() {
    // Let's create and trigger an Intent!
    // Grab the button that will trigger the Intent
    Button goToOrderFormButton = (Button) findViewById(R.id.mainActivityOrderFormButton);
    // Add onClickListener
    goToOrderFormButton.setOnClickListener(v -> { // <-- shorthand for new View.OnClickListner
      // Create an intent... Tell Intent where you're coming from and where you're going
      Intent goToOrderFormIntent = new Intent(MainActivity.this, OrderFormActivity.class);

      // to pass the Intent Extra, first grab the string value you want to pass
      String productName = ((EditText) findViewById(R.id.mainActivityInputEditText)).getText().toString();
      // Then, put the extra in the (already created) intent (give it a tag and a value)
      goToOrderFormIntent.putExtra(PRODUCT_NAME_EXTRA_TAG, productName);

      // Start Intent!
      startActivity(goToOrderFormIntent);
      // May also be written as MainActivity.this.startActivity(goToOrderFormIntent)
      // The "this" is only needed if starting the Intent from a different activity
    });
  }

}
