package com.reyjroliva.lecture28demo.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.Contact;
import com.amplifyframework.datastore.generated.model.Product;
import com.amplifyframework.datastore.generated.model.ProductCategoryEnum;
import com.reyjroliva.lecture28demo.R;


import java.io.FileNotFoundException;
import java.io.InputStream;
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
  ActivityResultLauncher<Intent> activityResultLauncher;
  private String s3Key;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_product);

    productTypeSpinner = findViewById(R.id.AddProductEnumTypeSpinner);

    // WARNING: The ActivityResultLauncher MUST be initialized in onCreate(), not in onResume() or a click handler! Otherwise it will fail
    activityResultLauncher = getImagePickingActivityResultLauncher();

    // Updated in class 33 demo
    contactsFuture = new CompletableFuture<>();
    contactSpinner = findViewById(R.id.addProductContactSpinner);

    setUpContactsSpinner();
    setupTypeSpinner();
    saveProduct();
    setupImageButton();
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
  public void saveProduct(){
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

      Product newProduct = Product.builder()
        .name(productName)
        .description("simple product description")
        .dateCreated(new Temporal.DateTime(new Date(), 0))
        .productCategory((ProductCategoryEnum) productTypeSpinner.getSelectedItem())
        .contactPerson(selectedContact)
        .s3Key(s3Key)
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

  // setup addImageButton
  public void setupImageButton(){
    findViewById(R.id.AddProductActivityBttnImageSelection).setOnClickListener(v -> {
      launchImageSelectionIntent();
    });
  }

   // LaunchImageSelectionIntent
  public void launchImageSelectionIntent(){
    Intent imageFilePickingIntent = new Intent(Intent.ACTION_GET_CONTENT);
    imageFilePickingIntent.setType("*/*");
    imageFilePickingIntent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{"image/jpeg", "image/jpg", "image/png"});

    activityResultLauncher.launch(imageFilePickingIntent);
  }

  // Activity result launcher method to initilize the actrivity result launcher
  private ActivityResultLauncher<Intent> getImagePickingActivityResultLauncher(){
    ActivityResultLauncher<Intent> imagePickingActivtyResultLauncher =
      registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
          @Override
          public void onActivityResult(ActivityResult result) {
            // Uri of image -> the path
            Uri pickedImageFileUri = result.getData().getData();
            try {
              InputStream pickedImageInputStream = getContentResolver().openInputStream(pickedImageFileUri);
              String pickedImageFileName = getFileNameFromUri(pickedImageFileUri);
              Log.i(TAG, "Succeeded in getting input stream from file on phone! Filename is:" + pickedImageFileName);
              uploadInputStreamToS3(pickedImageInputStream, pickedImageFileName, pickedImageFileUri);
            } catch (FileNotFoundException e) {
              throw new RuntimeException(e);
            }
          }
        }
      );
    return imagePickingActivtyResultLauncher;
  }

  // uploadInputStreamToS3
  public void uploadInputStreamToS3(InputStream pickedImageInputStream, String imageName, Uri pickedImageFileUri){
    Amplify.Storage.uploadInputStream(
      imageName,
      pickedImageInputStream,
      success -> {
        Log.i(TAG, "Succeeded in getting file uploaded to S3! Key is: " + success.getKey());
        s3Key = success.getKey();  // non-empty s3ImageKey globally indicates there is an image picked in this activity currently
        ImageView productImageView = findViewById(R.id.AddProductActivityBttnImageSelection);
        InputStream pickedImageInputStreamCopy = null;  // need to make a copy because InputStreams cannot be reused!
        try
        {
          pickedImageInputStreamCopy = getContentResolver().openInputStream(pickedImageFileUri);
        }
        catch (FileNotFoundException fnfe)
        {
          Log.e(TAG, "Could not get file stream from URI! " + fnfe.getMessage(), fnfe);
        }
        productImageView.setImageBitmap(BitmapFactory.decodeStream(pickedImageInputStreamCopy));
      },
      failure -> Log.e(TAG, "Upload failed", failure)
    );
  }


  // Taken from https://stackoverflow.com/a/25005243/16889809
  @SuppressLint("Range")
  public String getFileNameFromUri(Uri uri) {
    String result = null;
    if (uri.getScheme().equals("content")) {
      Cursor cursor = getContentResolver().query(uri, null, null, null, null);
      try {
        if (cursor != null && cursor.moveToFirst()) {
          result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
        }
      } finally {
        cursor.close();
      }
    }
    if (result == null) {
      result = uri.getPath();
      int cut = result.lastIndexOf('/');
      if (cut != -1) {
        result = result.substring(cut + 1);
      }
    }
    return result;
  }

}
