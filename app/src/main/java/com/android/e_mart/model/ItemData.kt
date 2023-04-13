package com.android.e_mart.model

data class ItemData(
    val limit: Int,
    val products: List<Products>,
    val skip: Int,
    val total: Int
)