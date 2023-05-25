package com.reyjroliva.lecture28demo.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.reyjroliva.lecture28demo.models.Product;

import java.util.List;

@Dao
public interface ProductDao {
  @Insert
  void insertAProduct(Product product);

  @Query("SELECT * FROM Product WHERE id = :id")
  Product findProductById(Long id);

  // FindALl
  @Query("SELECT * FROM Product")
  List<Product> findAllProducts();

  // FindAll by type
  @Query("SELECT * FROM Product WHERE type = :type")
  List<Product> findAllProductsbyType(Product.ProductTypeEnum type);

  @Update
  void updateProduct(Product product);

  // Delete
}
