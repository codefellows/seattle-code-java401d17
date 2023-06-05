package com.reyjroliva.lecture28demo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.Contact;
import com.amplifyframework.datastore.generated.model.Product;
import com.amplifyframework.datastore.generated.model.ProductCategoryEnum;
//import com.reyjroliva.lecture28demo.MainActivity;
import com.reyjroliva.lecture28demo.R;
//import com.reyjroliva.lecture28demo.models.Product;
//import com.reyjroliva.lecture28demo.models.ProductCategoryEnum;
//import com.reyjroliva.lecture28demo.models.Product;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AddProductActivity extends AppCompatActivity {
  public static final String TAG = "AddProductActivity";
  Spinner productTypeSpinner;
  // Updated in class 33 demo
  Spinner contactSpinner = null;
  CompletableFuture<List<Contact>> contactsFuture = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_product);

    productTypeSpinner = findViewById(R.id.AddProductEnumTypeSpinner);

    // Updated in class 33 demo
    contactsFuture = new CompletableFuture<>();
    contactSpinner = findViewById(R.id.addProductContactSpinner);
    setUpContactsSpinner();

    setupTypeSpinner();
    setupSaveBttn();
  }

  // Updated in class 33 demo
  public void setUpContactsSpinner() {
    Amplify.API.query(
      ModelQuery.list(Contact.class),
      success -> {
        Log.i(TAG, "Read contacts successfully");
        ArrayList<String> contactNames = new ArrayList<>();
        ArrayList<Contact> contacts = new ArrayList<>();
        for(Contact contact : success.getData()) {
          contacts.add(contact);
          contactNames.add(contact.getFullName());
        }
        contactsFuture.complete(contacts);

        runOnUiThread(() -> {
          contactSpinner.setAdapter(new ArrayAdapter<>(
            this,
            android.R.layout.simple_spinner_item,
            contactNames));
        });
      },
      failure -> {
        contactsFuture.complete(null); // Don't forget to complete a CompletableFuture on every code path!
        Log.i(TAG, "Did not read contacts successfully");
      }
    );
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
      //Updated in class 33 demo
      String selectedContactString = contactSpinner.getSelectedItem().toString();

      List<Contact> contacts = null;
      try {
        contacts = contactsFuture.get();
      } catch (InterruptedException ie) {
        Log.e(TAG, "InterruptedException while getting contacts");
        Thread.currentThread().interrupt();
      } catch (ExecutionException ee) {
        Log.e(TAG, "ExecutionException while getting contacts");
      }
      Contact selectedContact = contacts.stream().filter(c -> c.getFullName().equals(selectedContactString)).findAny().orElseThrow(RuntimeException::new);

      String productName = ((EditText)findViewById(R.id.AddProductETName)).getText().toString();
      //Product newProduct = new Product(
      //  ((EditText)findViewById(R.id.AddProductETName)).getText().toString(),
      //  "Test string",
      //  new Date(),
      //  ProductCategoryEnum.fromString(productTypeSpinner.getSelectedItem().toString())
      //);

      Product newProduct = Product.builder()
        .name(productName)
        .description("simple product description")
        .dateCreated(new Temporal.DateTime(new Date(), 0))
        .productCategory((ProductCategoryEnum) productTypeSpinner.getSelectedItem())
        .contactPerson(selectedContact)
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
