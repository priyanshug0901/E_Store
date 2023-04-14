package com.android.e_mart.repository

import com.android.e_mart.dao.CartDataAccessObject
import com.android.e_mart.dao.ProductTable
import javax.inject.Inject

class ProductDetailRepository @Inject constructor(private val dao: CartDataAccessObject) {

    suspend fun insertProductInDb(productTable: ProductTable): Long {
        return dao.insert(productTable)
    }
}