package com.android.e_mart.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "productsCart"
)
data class ProductTable (
    @PrimaryKey val id: Int,
    val brand: String?,
    val category: String?,
    val description: String?,
    val discountPercentage: Double,
    val price: Int,
    val rating: Double,
    val stock: Int,
    val thumbnail: String?,
    val title: String?
) : java.io.Serializable