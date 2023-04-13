package com.android.e_mart.model

import java.io.Serializable

data class Products(
    val brand: String?,
    val category: String?,
    val description: String?,
    val discountPercentage: Double,
    val id: Int,
    val images: List<String>?,
    val price: Int = 100 ,
    val rating: Double,
    val stock: Int,
    val thumbnail: String?,
    val title: String?
) : Serializable