package com.nytimes.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nytimes.utils.ListTypeConverter
import com.nytimes.data.entities.Book
import com.nytimes.data.entities.Seller

@Database(entities = [Seller::class, Book::class], version = 3, exportSchema = false)
@TypeConverters(ListTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moviesDao(): SellersDao
    abstract fun genreDao(): BooksDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "sellers")
                .fallbackToDestructiveMigration()
                .build()
    }

}