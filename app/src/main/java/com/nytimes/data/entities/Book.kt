package com.nytimes.data.entities

import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.nytimes.utils.ListTypeConverter
import java.io.Serializable


data class Book(
    var rank: String?,
    var list_name: String?,
    @TypeConverters(ListTypeConverter::class) var book_details: MutableList<BookDetails>? = null,
) : Serializable