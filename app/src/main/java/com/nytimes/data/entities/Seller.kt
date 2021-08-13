package com.nytimes.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.nytimes.utils.ListTypeConverter
import java.io.Serializable

@Entity(tableName = "seller")
data class Seller(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var list_name: String?,
    var display_name: String? = null,
    var update: String?= null,
    var newest_published_date: String?= null,
    @TypeConverters(ListTypeConverter::class) var book_details: MutableList<Book>?= null,
    var rank: Int= 0
) : Serializable