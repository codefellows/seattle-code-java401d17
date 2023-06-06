package com.reyjroliva.lecture28demo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.reyjroliva.lecture28demo.R;

public class SignupActivity extends AppCompatActivity {
  public static final String TAG = "SignupActivity";
  public static final String SIGN_UP_EMAIL_TAG = "Signup_Email_Tag";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signup);

    setUpSignUpButton();
  }

  public void setUpSignUpButton() {
    Button signUpButton = findViewById(R.id.signupActivitySignupButton);

    signUpButton.setOnClickListener(v -> {
      String userEmail = ((EditText) findViewById(R.id.signupActivityUsernameEditText)).getText().toString();
      String userPassword = ((EditText) findViewById(R.id.signupActivityPasswordEditText)).getText().toString();
      // Amplify User Sign Up code block
      Amplify.Auth.signUp(userEmail, // user email address as username in Cognito calls
        userPassword, // Cognito's default password policy is 8 characters, no other requirements
        AuthSignUpOptions.builder()
          .userAttribute(AuthUserAttributeKey.email(), userEmail)
          //.userAttribute(AuthUserAttributeKey.nickname(), "Rey")
          .build(),
        good -> {
          Log.i(TAG, "Signup succeeded: " + good.toString());
          // move to the verify account activity and pass the email as an intent extra
          Intent goToVerificationIntent = new Intent(SignupActivity.this, VerifyAccountActivity.class);
          goToVerificationIntent.putExtra(SIGN_UP_EMAIL_TAG, userEmail);
          startActivity(goToVerificationIntent);
        },
        bad -> {
          Log.i(TAG, "Signup failed with username: " + userEmail + "with this message: " + bad.toString());
        }
      );
    });
  }
}
