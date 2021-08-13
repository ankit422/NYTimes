package com.nytimes.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nytimes.data.entities.Book

@Dao
interface BooksDao {
    @Query("SELECT * FROM book where id IN (:ids)")
    fun getById(ids: List<Int>): LiveData<List<Book>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(genre: List<Book>)

}