package com.nytimes.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nytimes.data.entities.Seller

@Dao
interface SellersDao {
    @Query("SELECT * FROM seller order by newest_published_date")
    fun getAll(): LiveData<List<Seller>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(seller: List<Seller>)
}