package com.reyjroliva.lecture28demo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.reyjroliva.lecture28demo.MainActivity;
import com.reyjroliva.lecture28demo.R;
import com.reyjroliva.lecture28demo.database.ZorkMasterDatabase;
import com.reyjroliva.lecture28demo.models.Product;

import java.util.Date;

public class AddProductActivity extends AppCompatActivity {
  public static final String TAG = "AddProductActivity";
  ZorkMasterDatabase zorkMasterDatabase;
  Spinner productTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        productTypeSpinner = findViewById(R.id.AddProductEnumTypeSpinner);

      zorkMasterDatabase = Room.databaseBuilder(
          getApplicationContext(),
          ZorkMasterDatabase.class,
          MainActivity.DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build();

      setupTypeSpinner();
      setupSaveBttn();
    }

  public void setupTypeSpinner(){
    // setup spinner with adapter. Passing in ProductEnumType.values()
    productTypeSpinner.setAdapter(new ArrayAdapter<>(
      this,
      android.R.layout.simple_spinner_item,
      Product.ProductTypeEnum.values()
    ));
  }


  // setup save button
  public void setupSaveBttn(){
      // set onClick listner
    findViewById(R.id.AddProductSaveBttn).setOnClickListener(v -> {
      Product newProduct = new Product(
        ((EditText)findViewById(R.id.AddProductETName)).getText().toString(),
        new Date(),
        Float.parseFloat(((EditText)findViewById(R.id.AddProductETPrice)).getText().toString()),
        Product.ProductTypeEnum.fromString(productTypeSpinner.getSelectedItem().toString())
      );
      zorkMasterDatabase.productDao().insertAProduct(newProduct);
      Toast.makeText(this, "Product saved to database!", Toast.LENGTH_SHORT).show();
    });

  }
}
