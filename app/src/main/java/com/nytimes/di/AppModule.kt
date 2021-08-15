package com.nytimes.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nytimes.data.local.AppDatabase
import com.nytimes.data.local.SellersDao
import com.nytimes.data.remote.NetworkService
import com.nytimes.data.remote.RemoteDataSource
import com.nytimes.data.repository.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.nytimes.com/svc/books/v3/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCountryService(retrofit: Retrofit): NetworkService =
        retrofit.create(NetworkService::class.java)

    @Singleton
    @Provides
    fun provideCountryRemoteDataSource(networkService: NetworkService) =
        RemoteDataSource(networkService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideSellerDao(db: AppDatabase) = db.sellerDao()


    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        sellerDataSource: SellersDao
    ) =
        DataRepository(remoteDataSource, sellerDataSource)
}