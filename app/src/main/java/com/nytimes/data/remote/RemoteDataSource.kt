package com.nytimes.data.remote

import com.nytimes.data.remote.BaseDataSource
import com.nytimes.data.remote.NetworkService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val networkService: NetworkService
) : BaseDataSource() {

    suspend fun getSellers() = getResult { networkService.getAllSellers() }
    suspend fun searchMovies(query: String) = getResult { networkService.getSearchMovies(query) }
}