package com.android.e_mart.repository

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.android.e_mart.R
import com.android.e_mart.api.ApiServices
import com.android.e_mart.model.Products
import com.android.e_mart.model.ProductsViewData
import javax.inject.Inject

class SearchRepository  @Inject constructor(private val apiServices: ApiServices){

    suspend fun searchProduct(searchQuery: String): ProductsViewData {
        val response = apiServices.searchProducts(searchQuery)
        val productList: List<Products>? = response.body()?.products
        if(response.body()?.products?.isEmpty() == true){
            return ProductsViewData("getting empty List", productList)
        }
        return ProductsViewData("ok", productList)
    }


}