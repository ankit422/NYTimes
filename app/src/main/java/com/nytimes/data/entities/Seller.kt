package com.nytimes.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.nytimes.utils.DateTypeConverter
import java.io.Serializable
import java.util.*

@Entity(tableName = "seller")
data class Seller(
    @PrimaryKey var list_name: String = "",
    var display_name: String? = null,
    var updated: String? = null,
    @TypeConverters(DateTypeConverter::class) var newest_published_date: Date? = null,
    var rank: Int = 0
) : Serializable