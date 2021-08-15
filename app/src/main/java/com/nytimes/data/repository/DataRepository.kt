package com.nytimes.data.repository

import com.nytimes.data.local.SellersDao
import com.nytimes.data.remote.RemoteDataSource
import com.nytimes.utils.performGetOperation
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val sellerDataSource: SellersDao,
) {

    fun getSellerList() = performGetOperation(
        databaseQuery = { sellerDataSource.getAll() },
        networkCall = { remoteDataSource.getSellers() },
        saveCallResult = { sellerDataSource.insertAll(it.results) }
    )
}