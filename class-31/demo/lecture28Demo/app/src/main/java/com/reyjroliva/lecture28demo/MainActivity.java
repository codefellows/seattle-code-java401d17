package com.reyjroliva.lecture28demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Product;
import com.reyjroliva.lecture28demo.activities.AddProductActivity;
import com.reyjroliva.lecture28demo.activities.OrderFormActivity;
import com.reyjroliva.lecture28demo.activities.UserProfileActivity;
import com.reyjroliva.lecture28demo.adapters.ProductListRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  public static final String PRODUCT_NAME_EXTRA_TAG = "productName"; // at top of class, for other classes to be able to reference
  private final String TAG = "MainActivity";
  List<Product> products;
  ProductListRecyclerViewAdapter adapter;
  SharedPreferences preferences;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Updated in class 33 demo (comment out after saving to DB once!)
    //Contact contact1 = Contact.builder()
    //  .email("rey@example.com")
    //  .fullName("Rey Oliva")
    //  .build();
    //Amplify.API.mutate(
    //  ModelMutation.create(contact1),
    //  successRespose -> Log.i(TAG, "MainActivity.onCreate(): made a contact successfully"),
    //  failureResponse -> Log.i(TAG, "MainACtivity.onCreate(): contact failed with this response: " + failureResponse)
    //);
    //
    //Contact contact2 = Contact.builder()
    //  .email("alex@example.com")
    //  .fullName("Alex White")
    //  .build();
    //Amplify.API.mutate(
    //  ModelMutation.create(contact2),
    //  successRespose -> Log.i(TAG, "MainActivity.onCreate(): made a contact successfully"),
    //  failureResponse -> Log.i(TAG, "MainACtivity.onCreate(): contact failed with this response: " + failureResponse)
    //);
    //
    //Contact contact3 = Contact.builder()
    //  .email("ed@example.com")
    //  .fullName("Ed Younskevicious")
    //  .build();
    //Amplify.API.mutate(
    //  ModelMutation.create(contact3),
    //  successRespose -> Log.i(TAG, "MainActivity.onCreate(): made a contact successfully"),
    //  failureResponse -> Log.i(TAG, "MainACtivity.onCreate(): contact failed with this response: " + failureResponse)
    //);


    // TODO: SETUP DATABASE QUERY!
//    products = zorkMasterDatabase.productDao().findAllProducts();
    products = new ArrayList<>();

    setUpSettingsButton();
    setUpRecyclerView();
    setUpSubmitButton();
    setUpOrderFormButton();
    setupAddProductButton();
  }

  @Override
  protected void onResume() {
    super.onResume();
    products.clear();

    Amplify.API.query(
      ModelQuery.list(Product.class),
      success -> {
        Log.i(TAG, "Read products successfully!");
        products.clear();
        for(Product databaseProduct : success.getData()) {
          // Updated in class 33 demo
          // used for filtering, in lab the selected team will be done on the settings page
          /* hint: pass the selected team string to main activity via an intent or
           * !!save via sharedPreferences!! and then you can use that string to filter!
           * Make sure you delete Tasks with null Team values!
           */
          String contactName = "Rey Oliva";
          if(databaseProduct.getContactPerson().getFullName().equals(contactName)) {
            products.add(databaseProduct);
          }
        }

        //adapter.notifyDataSetChanged(); //since this runs asynch, teh adapter may have already been rendered, sp tell it to update again
        runOnUiThread(() -> adapter.notifyDataSetChanged());
      },
      failure -> Log.i(TAG, "Did not read products successfully!")
    );

    // TODO: SETUP DATABASE QUERY
//    products.addAll(zorkMasterDatabase.productDao().findAllProducts());

    adapter.notifyDataSetChanged();
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

  public void setUpRecyclerView() {
    // TODO: Step 1-2: (in main activity) grab your RecyclerView
    RecyclerView productListRecyclerView = (RecyclerView) findViewById(R.id.mainActivityProductListRecyclerView);

    // TODO: Step 1-3: (in main activity) set the layout manager of the RecyclerView to a LinearLayoutManager
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    // if you want a horizontal list:
    //((LinearLayoutManager)layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
    productListRecyclerView.setLayoutManager(layoutManager);

    // TODO: Step 1-5: (in main activity) Create and attach the RecylcerView.adapter
    //ProductListRecyclerViewAdapter adapter = new ProductListRecyclerViewAdapter();
    //productListRecyclerView.setAdapter(adapter);
    // TODO: Step 2-3 cont.: change the creation of the adapter to take the list of data items
    //ProductListRecyclerViewAdapter adapter = new ProductListRecyclerViewAdapter(products);
    //productListRecyclerView.setAdapter(adapter);
    // TODO: Step 3-2: Hand in the activity context to the RecyclerViewAdapater creation
    adapter = new ProductListRecyclerViewAdapter(products, this);
    productListRecyclerView.setAdapter(adapter);
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

  public void setupAddProductButton(){
    findViewById(R.id.MainActivityAddProductButton).setOnClickListener(view -> {
      Intent goToAddProductActivity = new Intent(this, AddProductActivity.class);
      startActivity(goToAddProductActivity);
    });
  }

}

// Steps for adding Amplify to your app
// 1. Remove Room from your app
//   1A. Delete the Gradle Room dependencies in app's (lower-level) build.gradle
//   1B. Delete database class
//   1C. Delete DAO class
//   1D. Remove `@Entity` and `@PrimaryKey` annotations from the Product model class
//   1E: Delete the database variables and instantiation from each Activity that uses them
//   1F: Comment out DAO usages in each Activity that uses them
// CHECK APP HERE
// 2. Make an IAM user
// 3. Run `amplify configure`
// 4. Add Amplify Gradle dependencies in build.gradle files
// 5. Run `amplify init`
// 6. Run `amplify add api` (or `amplify update api`)
// 7. Run `amplify push`
//CHECK APP HERE
// 8. Change model in "amplify/backend/api/amplifyDatasource/schema.graphql" to match your app's model
// 9. Run `amplify api update` -> Disable conflict resolution
// 10. Run `amplify push --allow-destructive-graphql-schema-updates`
// 11. Run `amplify codegen models`
//CHECK APP HERE
// 12A. Add an application class that extends Application and configures Amplify
// 12B. Put the application class name in your AndroidManifest.xml
// 12C. Uninstall the app on your emulator
//CHECK APP HERE (may need to add cognito to dependencies)
// 13. Convert every usage of model classes to use Amplify generated models in app/src/main/java/com/amplifyframework/datastore/generated/model
//   13A. Instantiate classes using builder
//   13B. Get data elements via getters (if you aren't already)
//   13C. Delete your previous model from the models package
//CHECK APP HERE
// 14. Convert all DAO usages to Amplify.API calls
//CHECK APP HERE
// 15. Update RecyclerView adapter's collection via runOnUiThread()
//CHECK APP HERE
// 16. Fix date output in RecyclerView items
//CHECK APP HERE

