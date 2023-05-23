package com.reyjroliva.practice27.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.reyjroliva.practice27.MainActivity;
import com.reyjroliva.practice27.R;

public class OrderFormActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_order_form);

    setUpOrderFormInfo();
  }

  public void setUpOrderFormInfo() {
    Intent callingIntent = getIntent();
    String productNameString = null;

    if(callingIntent != null) {
      productNameString = callingIntent.getStringExtra(MainActivity.PRODUCT_NAME_EXTRA_TAG);
    }

    TextView orderFormInfoTextView = (TextView) findViewById(R.id.orderFormActivityInfoTextView);
    if(productNameString != null) {
      orderFormInfoTextView.setText(productNameString);
    } else {
      orderFormInfoTextView.setText(R.string.order_form_info); // kinda redundant but good to have... especially if we were to put this in the onResume()
    }
  }
}
