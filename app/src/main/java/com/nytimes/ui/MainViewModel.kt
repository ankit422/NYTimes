package com.nytimes.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nytimes.data.entities.BookData
import com.nytimes.data.entities.Seller
import com.nytimes.data.remote.RemoteDataSource
import com.nytimes.data.repository.DataRepository
import com.nytimes.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    repository: DataRepository
) : ViewModel() {

    private val _allSellers = repository.getSellerList()
    val allSellers: LiveData<Resource<List<Seller>>> = _allSellers

    val details = MutableLiveData<BookData>()

    fun getBooks(name: String?) {
        viewModelScope.launch {
            try {
                details.postValue(remoteDataSource.getBooks(name!!)!!)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
