package com.reyjroliva.lecture28demo.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.reyjroliva.lecture28demo.dao.ProductDao;
import com.reyjroliva.lecture28demo.models.Product;

@TypeConverters({ZorkMasterDatabaseConverters.class})
@Database(entities = {Product.class}, version = 1)
public abstract class ZorkMasterDatabase extends RoomDatabase {
  public abstract ProductDao productDao();
}
