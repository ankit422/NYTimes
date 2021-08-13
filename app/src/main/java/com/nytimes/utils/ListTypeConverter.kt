package com.nytimes.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nytimes.data.entities.Book

class ListTypeConverter {
    @TypeConverter
    fun toGenre(json: String): MutableList<Book>? {
        val type = object : TypeToken<MutableList<Book>?>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun toJson(genres: MutableList<Book>?) = Gson().toJson(genres)
}