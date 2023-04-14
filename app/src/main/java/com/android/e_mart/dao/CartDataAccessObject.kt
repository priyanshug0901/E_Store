package com.android.e_mart.dao

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CartDataAccessObject {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: ProductTable): Long

    @Query("SELECT * FROM productsCart")
    fun getAllProducts(): LiveData<List<ProductTable>>

    @Delete
    fun deleteProduct(product: ProductTable)

    @Query("DELETE FROM productsCart")
    suspend fun deleteAllProducts()

    @Query("SELECT SUM(price) FROM productsCart")
    fun totalPriceOfCart(): Int
}