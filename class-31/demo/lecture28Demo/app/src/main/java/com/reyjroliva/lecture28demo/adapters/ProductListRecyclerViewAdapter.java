package com.reyjroliva.lecture28demo.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Product;
import com.reyjroliva.lecture28demo.MainActivity;
import com.reyjroliva.lecture28demo.R;
import com.reyjroliva.lecture28demo.activities.OrderFormActivity;
//import com.reyjroliva.lecture28demo.models.Product;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

// TODO: Step 1-4: create a  class whose sole purpose is to manager RecyclerView (a RecyclerView Adapter!)
//TODO: Step 3-1: Clean up RecyclerViewAdapter references to actually use <ProductListRecyclerViewAdatper.ProductListViewHolder>
public class ProductListRecyclerViewAdapter extends RecyclerView.Adapter<ProductListRecyclerViewAdapter.ProductListViewHolder> {
  public static final String TAG = "ProductListRecyclerViewAdapter";

  // TODO: Step 2-3 cont.: in RecyclerViewAdapter, at top of class add a list of the data items as a field
  private List<Product> products;
  //TODO: Step 3-2 cont.: In rRecyclerViewAdapter, at top of class add a callingActivty Context as a field
  Context callingActivity;

  // TODO: Step 2-3 cont.: Edit the RecyclerViewAdapter to handle a list of data items
  // TODO: Step 3-2 cont.: Change the RecyclerViewAdapter's constructor to take in a callingActivity context
  public ProductListRecyclerViewAdapter(List<Product> products, Context callingActivity) {
    this.products = products;
    this.callingActivity = callingActivity;
  }

  //TODO: Step 3-1 cont.: Change the onCreateViewHolder for the adapter to return ProductListViewHolder
  @NonNull
  @Override
  public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    // TODO: Step 1-7: Inflate fragment
    View productFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_product_list, parent, false);
    // TODO: Step 1-9: Attach the fragment to the ViewHolder
    return new ProductListViewHolder(productFragment);
  }

  // TODO: Step 3-1 cont.: Change onBindViewHolder to take ProductListViewHolder as a parameter
  @Override
  public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {
    // TODO: Step 2-4: (in RecyclerView.Adapter.onBindViewHolder()) Bind the data items to the fragments inside of the ViewHolders
    TextView productFragmentTextView = (TextView) holder.itemView.findViewById(R.id.productFragmentTextView);
    String productName = products.get(position).getName();

    // Add date created to fragment
    DateFormat dateCreatedIso8601InputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
    dateCreatedIso8601InputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    DateFormat dateCreatedOutputFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
    dateCreatedOutputFormat.setTimeZone(TimeZone.getDefault());
    String dateCreatedCreateString = "";

    try{
      Date dateCreatedJavaDate = dateCreatedIso8601InputFormat.parse(products.get(position).getDateCreated().format());
      if(dateCreatedJavaDate != null) {
        dateCreatedCreateString = dateCreatedOutputFormat.format(dateCreatedJavaDate);
      }
    } catch (ParseException e) {
      Log.e(TAG, "failed to parse and format data with stack trace: " +  e);
      e.printStackTrace();
    }


    String productFragmentText =  position + "." + productName + " Date created: " + dateCreatedCreateString;
    productFragmentTextView.setText(productFragmentText);

    // TODO: Step 3-3: Create an onClickListener, make a intent inside it, and call this intent with an extra to go to your details page activity
    //View productViewHolder = holder.itemView;
    productFragmentTextView.setOnClickListener(v -> {
      Intent goToOrderFormIntent = new Intent(callingActivity, OrderFormActivity.class);
      goToOrderFormIntent.putExtra(MainActivity.PRODUCT_NAME_EXTRA_TAG, productName);
      callingActivity.startActivity(goToOrderFormIntent);
    });

  }

  @Override
  public int getItemCount() {
    // TODO: Step 1-10: determine size of data set (for testing we'll just use a big value)
    //return 100;
    // TODO: Step 2-5: Make the size of the list dynamic
    return products.size();
  }

  // TODO: Step 1-8: Make a ViewHolder class to hold a fragment
  public static class ProductListViewHolder extends RecyclerView.ViewHolder {
    public ProductListViewHolder(View fragmentItemView) {
      super(fragmentItemView);
    }
  }
}
