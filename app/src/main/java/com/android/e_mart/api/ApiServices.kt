package com.android.e_mart.api

import com.android.e_mart.model.ItemData
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    @GET("products")
    suspend fun getProduct(): Response<ItemData>
}