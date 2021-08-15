package com.nytimes.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val networkService: NetworkService
) : BaseDataSource() {

    suspend fun getSellers() = getResult { networkService.getAllSellers() }
    suspend fun getBooks(query: String) = getParsedResult { networkService.getBooks(query) }
}