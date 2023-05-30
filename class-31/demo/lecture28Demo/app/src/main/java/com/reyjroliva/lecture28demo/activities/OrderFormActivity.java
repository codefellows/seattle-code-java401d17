package com.reyjroliva.lecture28demo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.reyjroliva.lecture28demo.MainActivity;
import com.reyjroliva.lecture28demo.R;

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

    TextView orderFormInfoTextView = (TextView) findViewById(R.id.orderFormActivityOrderFormInfoTextView);
    if (productNameString != null) {
      orderFormInfoTextView.setText(productNameString);
    } else {
      orderFormInfoTextView.setText(R.string.no_product_name);
    }
  }

}
