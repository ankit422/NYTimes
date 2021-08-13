package com.nytimes.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nytimes.data.entities.Seller

@Dao
interface SellersDao {
    @Query("SELECT * FROM seller")
    fun getAll(): LiveData<List<Seller>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<Seller>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Seller)
//
//    @Query("SELECT * FROM seller WHERE ID = :id")
//    fun getMovie(id: Int): LiveData<Seller>
}