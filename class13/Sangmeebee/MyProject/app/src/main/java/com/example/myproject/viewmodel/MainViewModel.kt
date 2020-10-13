package com.example.myproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myproject.data.model.Items
import com.example.myproject.data.repository.NaverRepository
import com.example.myproject.data.repository.NaverRepositoryImpl
import com.example.myproject.data.source.local.NaverLocalDataSourceImpl
import com.example.myproject.data.source.remote.NaverRemoteDataSourceImpl

class MainViewModel : ViewModel() {

    private val repository: NaverRepository =
        NaverRepositoryImpl(NaverLocalDataSourceImpl(), NaverRemoteDataSourceImpl())
    val query = MutableLiveData<String>()
    val movieList = MutableLiveData<List<Items>>()
    val titleList = MutableLiveData<List<String>>()
    val showDialog = MutableLiveData<Unit>()
    var msg = MutableLiveData<String>()

    fun callMovieList() {
        val query = query.value ?: return
        if (query.isEmpty()) {
            msg.value = "empty"
        } else {
            repository.getMovieList(query, {
                if (it.isEmpty()) {
                    msg.value = "empty_result"
                } else {
                    msg.value = "success"
                    movieList.value = it
                }
            }, {
                msg.value = "error"
            })
        }
    }

    fun callDialog() {
        showDialog.value = Unit
    }

    fun callTitleList() {
        titleList.value = repository.readRecentSearchTitle()
    }
}
