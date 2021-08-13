package com.nytimes.data.repository

import com.movielist.utils.performGetClickedOperation
import com.movielist.utils.performGetOperation
import com.nytimes.data.local.BooksDao
import com.nytimes.data.local.SellersDao
import com.nytimes.data.remote.RemoteDataSource
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val sellerDataSource: SellersDao,
    private val genresDoa: BooksDao,
) {

    fun getSellerList() = performGetOperation(
        databaseQuery = { sellerDataSource.getAll() },
        networkCall = { remoteDataSource.getSellers() },
        saveCallResult = { sellerDataSource.insertAll(it.results) }
    )

    fun getMovieForSearch(query: String) = performGetOperation(
        databaseQuery = { sellerDataSource.getAll() },
        networkCall = { remoteDataSource.searchMovies(query) },
        saveCallResult = { sellerDataSource.insertAll(it.results) }
    )
}