package com.nytimes.data.entities


data class SellerList(
    var results: List<Seller>,
    var last_modified: String,
    var num_results: Int
)