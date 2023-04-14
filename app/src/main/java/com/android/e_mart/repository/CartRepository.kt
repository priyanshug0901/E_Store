package com.android.e_mart.repository

import androidx.lifecycle.LiveData
import com.android.e_mart.dao.CartDataAccessObject
import com.android.e_mart.dao.ProductTable
import javax.inject.Inject

class CartRepository @Inject constructor(private val dao: CartDataAccessObject) {
    fun removeItemFromCart(productTable: ProductTable) {
        return dao.deleteProduct(productTable)
    }

    fun getAllItem(): LiveData<List<ProductTable>> {
        return dao.getAllProducts()
    }

    fun getTotalCardPrice(): Int {
        return dao.totalPriceOfCart()
    }

    suspend fun deleteAllItemsFromCart() {
        return dao.deleteAllProducts()
    }
}