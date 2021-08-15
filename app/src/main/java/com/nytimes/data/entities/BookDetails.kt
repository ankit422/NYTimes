package com.nytimes.data.entities

import java.io.Serializable

data class BookDetails(
    var title: String?,
    var description: String?,
    var author: String?,
    var contributor: String?,
    var publisher: String?,
    var price: String?,
) : Serializable