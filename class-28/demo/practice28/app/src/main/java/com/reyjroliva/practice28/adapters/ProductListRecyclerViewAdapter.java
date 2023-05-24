package com.reyjroliva.practice28.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.reyjroliva.practice28.R;

// TODO: Step 1-4: Make a new class whose sole purpose is to manage RecyclerViews: RecyclerView.Adapter
public class ProductListRecyclerViewAdapter extends RecyclerView.Adapter {
  // TODO: Step 1-8: (in RecyclerViewAdapter) make a ViewHolder class to hold a Fragment
  public static class ProductListViewHolder extends RecyclerView.ViewHolder {
    public ProductListViewHolder(View fragmentItemView) {
      super(fragmentItemView);
    }
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    // TODO: Step 1-7: Inflate the fragment
    View productFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_product_list, parent, false);
    // TODO: Step 1-8: attach the fragment to the ViewHolder
    return new ProductListViewHolder(productFragment);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

  }

  @Override
  public int getItemCount() {
    // TODO: Step 1-10: return the count for the numder of item fragments (for testing purposes, start with something super big)
    return 100;
  }
}
