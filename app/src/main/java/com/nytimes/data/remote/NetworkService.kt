package com.nytimes.data.remote

import com.nytimes.data.entities.BookData
import com.nytimes.data.entities.SellerList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("lists/names.json?api-key=3tANsey1FwCT7rRryjqemQIAksD0WxPz")
    suspend fun getAllSellers(): Response<SellerList>

    @GET("lists.json?api-key=3tANsey1FwCT7rRryjqemQIAksD0WxPz")
    suspend fun getBooks(@Query("list") q: String): Response<BookData>
}