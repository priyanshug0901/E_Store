package com.android.e_mart.api

import com.android.e_mart.model.ItemData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("products")
    suspend fun getProduct(): Response<ItemData>

    @GET("products/search")
    suspend fun searchProducts(@Query("q") searchQuery: String = "phone"): Response<ItemData>
}