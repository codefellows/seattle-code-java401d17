package com.reyjroliva.lecture28demo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;
import com.reyjroliva.lecture28demo.MainActivity;
import com.reyjroliva.lecture28demo.R;

public class LoginActivity extends AppCompatActivity {
  public static final String TAG = "LoginAcivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    setUpLoginButton();
    setUpSignUpButton();
  }

  public void setUpLoginButton() {
    Intent callingIntent = getIntent();
    String userEmail = callingIntent.getStringExtra(VerifyAccountActivity.VERIFICATION_EMAIL_TAG);
    EditText userEmailEditText = findViewById(R.id.loginActivityUsernameEditText);
    if(userEmail != null) {
      userEmailEditText.setText(userEmail);
    }
    EditText userPasswordEditText = findViewById(R.id.loginActivityPasswordEditText);
    Button loginButton = findViewById(R.id.loginActivityLoginButton);

    loginButton.setOnClickListener(v -> {
      // Amplify User Login code block
      Amplify.Auth.signIn(userEmailEditText.getText().toString(),
        userPasswordEditText.getText().toString(),
        success -> {
          Log.i(TAG, "Login succeeded: " + success.toString());
          Intent goToMainActivity = new Intent(LoginActivity.this, MainActivity.class);
          startActivity(goToMainActivity);
        },
        failure -> {
          Log.i(TAG, "Login failed: " + failure.toString());
        });
    });
  }

  public void setUpSignUpButton() {
    Button signUpButon = findViewById(R.id.loginActivitySignUpButton);

    signUpButon.setOnClickListener(v -> {
      Intent goToSignUpActivity = new Intent(LoginActivity.this, SignupActivity.class);
      startActivity(goToSignUpActivity);
    });
  }
}
