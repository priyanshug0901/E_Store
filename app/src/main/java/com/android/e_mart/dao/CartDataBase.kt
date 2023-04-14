package com.android.e_mart.dao

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ProductTable::class],
    version = 2, exportSchema = false
)
abstract class CartDataBase : RoomDatabase() {
    abstract fun getCartDao(): CartDataAccessObject
}