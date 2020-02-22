package com.example.myapplication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.model.MovieResult
import com.example.myapplication.data.repository.NaverRepository

class MainViewModel(private val naverRepository: NaverRepository) : ViewModel() {

    val query = MutableLiveData<String>()
    val searchResultList = MutableLiveData<List<MovieResult.Item>>()
    val errorQueryBlank = MutableLiveData<Throwable>()
    val errorFailSearch = MutableLiveData<Throwable>()
    val resultEmpty = MutableLiveData<Boolean>()

    fun findMovie() {
        if (query.value.toString().isBlank()) {
            errorQueryBlank.value = Throwable()
        } else {
            naverRepository.getResultData(
                query.value ?: "",
                success = {
                    if (it.items.isEmpty()) {
                        searchResultList.value = it.items
                        resultEmpty.value = true
                    } else {
                        searchResultList.value = it.items
                        resultEmpty.value = false
                    }
                },
                fail = {
                    errorFailSearch.value = it
                })
        }
    }

    fun getRecentData() {
        naverRepository.getRecentData().let {
            searchResultList.value = it.results
            query.value = it.query
        }
    }

}