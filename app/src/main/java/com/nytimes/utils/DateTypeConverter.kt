package com.nytimes.utils

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class DateTypeConverter {
    @TypeConverter
    fun toDate(_date: String): Date? {
        var date: Date? = null
        val format = SimpleDateFormat("yyyy-MM-dd")
        try {
            date = format.parse(_date)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return date
    }

    @TypeConverter
    fun toString(_date: Date?): String? {
        try {
            return SimpleDateFormat("yyyy-MM-dd").format(_date)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}