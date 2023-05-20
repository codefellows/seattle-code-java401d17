package com.reyjroliva.practicelecture26;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.reyjroliva.practicelecture26.activites.OrderFormActivity;

import java.util.concurrent.LinkedBlockingDeque;

public class MainActivity extends AppCompatActivity {
  private final String TAG = "MainActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    // Steps for adding functionality to webpage UIs in JS
    // 1: get UI element by Id
    // 2: add event listener to the element
    // 3: attach a callback function with an onClick() method
    // 4: do stuff in the callback method

    // add functionality to Android UI elements in Java
    // Step 1: grab element using findViewById()
    // grabbing a view, assigning it to a variable, and setting the value in 2 lines
    Button submitButton = (Button) findViewById(R.id.mainActivitySubmitButton);
    submitButton.setText("button"); // <-- "button" can't be translated.. use a string from your res files!
    //grabbing a view and setting the value in one line (using a string resource)
    ((Button) findViewById(R.id.mainActivitySubmitButton)).setText(R.string.main_activity_submit_button);

    // Step 2/3: add onClickListener
    submitButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // Step 4: Do stuff in the callback
        System.out.println("Submit button pressed!");

        // A better way to log
        Log.v(TAG, "I'M A VERBOSE LOG");
        Log.d(TAG, "I'M A DEBUG LOG");
        Log.i(TAG, "I'M AN INFO LOG");
        Log.w(TAG, "I'M A WARNING LOG");
        Log.e(TAG, "I'M AN ERROR LOG");
        Log.wtf(TAG, "WHAT A TERRIBLE FAILURE");

        // Logging an exception
        //Log.i(TAG, "insert your exception message here", new RuntimeException());
        try {
          // some stuff
        } catch (RuntimeException re) {
          Log.e(TAG, "we caught a runtime exception", re);
        }

        // Changing another view's value within onClick method
        ((EditText) findViewById(R.id.mainActivityProductNameEditText)).setText("Submitted!");
      }
    });

    // Let's create and trigger an Intent!
    // Grab the button that will trigger the Intent
    Button goToOrderFormButton = findViewById(R.id.mainActivityGoToOrderForm);
    // Add onClickListener
    goToOrderFormButton.setOnClickListener(v -> { // <--- shorthand for "new View.onClickListner() {"
      // Create an Intent.. Tell it where you are coming from and where you are going
      Intent goToOrderFormIntent = new Intent(MainActivity.this, OrderFormActivity.class);
      // Start the Intent!
      startActivity(goToOrderFormIntent);
      // May also be written as MainActivity.this.startActivity(goToOrderFormIntent)
      // The "this" is only needed if starting the Intent from a different activity
    });
  }
}
