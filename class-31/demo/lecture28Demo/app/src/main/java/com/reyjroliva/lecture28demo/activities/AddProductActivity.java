package com.reyjroliva.lecture28demo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.Product;
import com.amplifyframework.datastore.generated.model.ProductCategoryEnum;
//import com.reyjroliva.lecture28demo.MainActivity;
import com.reyjroliva.lecture28demo.R;
//import com.reyjroliva.lecture28demo.models.Product;


import java.util.Date;

public class AddProductActivity extends AppCompatActivity {
  public static final String TAG = "AddProductActivity";
  Spinner productTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        productTypeSpinner = findViewById(R.id.AddProductEnumTypeSpinner);


      setupTypeSpinner();
      setupSaveBttn();
    }

  public void setupTypeSpinner(){
    // setup spinner with adapter. Passing in ProductEnumType.values()
    productTypeSpinner.setAdapter(new ArrayAdapter<>(
      this,
      android.R.layout.simple_spinner_item,
      ProductCategoryEnum.values()
    ));
  }


  // setup save button
  public void setupSaveBttn(){
      // set onClick listner
    findViewById(R.id.AddProductSaveBttn).setOnClickListener(v -> {
      String productName = ((EditText)findViewById(R.id.AddProductETName)).getText().toString();
      //Product newProduct = new Product(
      //  ((EditText)findViewById(R.id.AddProductETName)).getText().toString(),
      //  new Date(),
      //  Float.parseFloat(((EditText)findViewById(R.id.AddProductETPrice)).getText().toString()),
      //  Product.ProductTypeEnum.fromString(productTypeSpinner.getSelectedItem().toString())
      //);

      Product newProduct = Product.builder()
        .name(productName)
        .description("simple product description")
        .dateCreated(new Temporal.DateTime(new Date(), 0))
        .productCategory((ProductCategoryEnum) productTypeSpinner.getSelectedItem())
        .build();

      Amplify.API.mutate(
        ModelMutation.create(newProduct), // making a GraphWL request to the cloud
        successResponse -> Log.i(TAG, "AddProductActivity.onCreate().setUpSaveButton(): made a new product successfully"),
        failureResponse -> Log.i(TAG, "AddProductActivity.onCreate().setUpSaveButton(): failed with this response: " + failureResponse)
      );

      // TODO FIX THE DATABASE SAVE!
//      zorkMasterDatabase.productDao().insertAProduct(newProduct);
      Toast.makeText(this, "Product saved to database!", Toast.LENGTH_SHORT).show();
    });

  }
}
