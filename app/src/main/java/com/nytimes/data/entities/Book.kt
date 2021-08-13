package com.nytimes.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "book")
data class Book(
    @PrimaryKey var id: Int?,
    var title: String?,
    var description: String?,
    var author: String?,
    var contributor: String?,
    var publisher: String?,
    var price: String?,
) : Serializable