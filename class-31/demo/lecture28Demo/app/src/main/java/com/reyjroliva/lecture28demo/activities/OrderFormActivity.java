package com.reyjroliva.lecture28demo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Product;
import com.reyjroliva.lecture28demo.MainActivity;
import com.reyjroliva.lecture28demo.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class OrderFormActivity extends AppCompatActivity {
  public final String TAG = "OrderFormActivity";
  private String productId;
  private final MediaPlayer mp = new MediaPlayer();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_order_form);

    setUpOrderFormInfo();
    setUpSpeakButton();
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

  public void setUpSpeakButton() {
    Button speakbutton = findViewById(R.id.orderFormActivitySpeakButton);
    speakbutton.setOnClickListener(v -> {
      String productName; // set this from a TextView or EditTest or whatever is holding your product name
      productName = ((TextView)findViewById(R.id.orderFormActivityOrderFormInfoTextView)).getText().toString();

      Amplify.Predictions.convertTextToSpeech(
        productName,
        success -> playAudio(success.getAudioData(), productName),
        failure -> Log.e(TAG, "Audio conversion of product, " + productName + ", failed", failure)
      );
    });
  }

  // Display Image Method
  private void displayProductImage(){
    // does the Product have a good S3Key?
      // If no, don't display anything!
    // Make an amplify call to S3 with Products s3 key.
      // Returns the image. Saves to phone, gives you the URI
//     Dsiplay image to screen
  }

  private void playAudio(InputStream data, String textToSpeak) {
    File mp3File = new File(getCacheDir(), "audio.mp3");

    try(OutputStream out = new FileOutputStream(mp3File)) {
      byte[] buffer = new byte[8 * 1024];
      int bytesRead;

      while((bytesRead = data.read(buffer)) != -1) {
        out.write(buffer, 0, bytesRead);
      }

      Log.i(TAG, "audio file finished reading");

      mp.reset();
      mp.setOnPreparedListener(MediaPlayer::start);
      mp.setDataSource(new FileInputStream(mp3File).getFD());
      mp.prepareAsync();

      Log.i(TAG, "Audio played");
      Log.i(TAG, "text to speak: " + textToSpeak);
    } catch(IOException error) {
      Log.e(TAG, "Error writing audio file");
    }
  }

}
