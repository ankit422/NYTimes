package com.nytimes.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nytimes.data.entities.Seller
import com.nytimes.data.repository.DataRepository
import com.nytimes.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository: DataRepository) : ViewModel() {

    private val _allSellers = repository.getSellerList()
    val allSellers: LiveData<Resource<List<Seller>>> = _allSellers
    private val _id = MutableLiveData<Int>()
    val query = MutableLiveData<String>()
    private val _gIds = MutableLiveData<List<Int>>()


//    val allSellers: LiveData<Resource<List<Seller>>> = _allSellers

    fun start(id: Int?) {
        _id.value = id!!
    }
}
