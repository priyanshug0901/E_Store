package com.android.e_mart.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.android.e_mart.model.Products
import retrofit2.http.DELETE

@Dao
interface CartDataAccessObect {

    @Insert
    suspend fun insert(product: ProductTable) : Long

    @Query("SELECT * FROM productsCart")
    fun getAllProducts() : LiveData<List<ProductTable>>

    @Delete
    fun deleteProduct(product: ProductTable)

    @Query("DELETE FROM productsCart")
    suspend fun deleteAllProducts()

    @Query("SELECT SUM(price) FROM productsCart")
    fun totalPriceOfCart() : Int
}