package com.nytimes.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nytimes.utils.ListTypeConverter
import com.nytimes.data.entities.Book
import com.nytimes.data.entities.Seller
import com.nytimes.utils.DateTypeConverter

@Database(entities = [Seller::class], version = 11, exportSchema = false)
@TypeConverters(ListTypeConverter::class, DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun sellerDao(): SellersDao

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