package com.hjhan.hyejeong.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hjhan.hyejeong.data.model.Item
import com.hjhan.hyejeong.data.repository.NaverRepository

class MainViewModel @ViewModelInject constructor(private val repository: NaverRepository) :
    ViewModel() {

    val query = MutableLiveData<String>()
    val movieList = MutableLiveData<List<Item>>()
    val onFailedEvent = MutableLiveData<Unit>()
    val onEmptyQuery = MutableLiveData<Unit>()
    val queryList = MutableLiveData<List<String>>()

    fun setMovieList() {
        if (query.value.isNullOrBlank()) {
            // 검색어 없을때 처리
            onEmptyQuery.value = Unit

        } else {
            repository.getSearchMovies(
                query = query.value!!,
                success = {
                    it.items.run {
                        //항상 리스트 통으로 넘겨줘야함.
                        movieList.value = it.items
                    }
                },
                failed = {
                    onFailedEvent.value = Unit
                })
        }

    }

    fun getRecentQueryList() {
        queryList.value = repository.getQueryList()
    }

}