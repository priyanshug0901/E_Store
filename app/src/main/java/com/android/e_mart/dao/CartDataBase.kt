package com.android.e_mart.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [ProductTable::class],
    version = 2, exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class CartDataBase :RoomDatabase(){
    abstract fun getCartDao(): CartDataAccessObject
}