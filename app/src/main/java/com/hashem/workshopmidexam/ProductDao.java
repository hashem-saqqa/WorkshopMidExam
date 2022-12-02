package com.hashem.workshopmidexam;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    public long insertProduct(Product product);

    @Query("SELECT * FROM Product ORDER BY productName")
    public List<Product> getAllProducts();

    @Delete
    public int deleteProduct(Product product);
}
