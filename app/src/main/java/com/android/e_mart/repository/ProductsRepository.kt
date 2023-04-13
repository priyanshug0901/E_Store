package com.android.e_mart.repository

import com.android.e_mart.api.ApiServices
import com.android.e_mart.model.Products
import com.android.e_mart.model.ProductsViewData
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ProductsRepository @Inject constructor(private val apiServices: ApiServices) {
    suspend fun getProducts() : ProductsViewData {
        val response = apiServices.getProduct()
        val productList: List<Products>? = response.body()?.products
        if(response.body()?.products?.isEmpty() == true){
            return ProductsViewData("getting empty List", productList)
        }
        return ProductsViewData("ok", productList)
    }
}