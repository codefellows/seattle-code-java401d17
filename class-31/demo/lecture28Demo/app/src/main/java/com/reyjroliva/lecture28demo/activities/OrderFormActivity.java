package com.reyjroliva.lecture28demo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Product;
import com.reyjroliva.lecture28demo.MainActivity;
import com.reyjroliva.lecture28demo.R;

public class OrderFormActivity extends AppCompatActivity {
  private String productId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_order_form);

    setUpOrderFormInfo();
  }

  public void setUpOrderFormInfo() {
    Intent callingIntent = getIntent();
    String productNameString = null;

    Amplify.API.query(
      ModelQuery.get(Product.class, productId),
      success -> {
        // DSIPLAY THE IMAGE
        // Downlaod the iamge from s3
          // s3Key + success callbakc + failure callback
        // grab imageView from activity
        // imageView.setImageBitmap(BitmapFactory.decodeFile(success.getFile().getPath()))
      },
      failure -> {}
    );

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

  // Display Image Method
  private void displayProductImage(){
    // does the Product have a good S3Key?
      // If no, don't display anything!
    // Make an amplify call to S3 with Products s3 key.
      // Returns the image. Saves to phone, gives you the URI
//     Dsiplay image to screen
  }

}
