package com.android.e_mart.dao

import androidx.room.TypeConverters


class TypeConverter {
    @androidx.room.TypeConverter
    fun fromListIntToString(intList: List<String>): String = intList.toString()
    @androidx.room.TypeConverter
    fun toListIntFromString(stringList: String): List<String> {
        val result = ArrayList<String>()
        val split =stringList.replace("[","").replace("]","").replace(" ","").split(",")
        for (n in split) {
            try {
                result.add(n)
            } catch (e: Exception) {

            }
        }
        return result
    }
}