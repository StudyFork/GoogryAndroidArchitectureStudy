package com.example.androidarchitecturestudy.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidarchitecturestudy.data.model.Movie
import com.example.androidarchitecturestudy.data.model.QueryHistory
import com.example.androidarchitecturestudy.data.repository.NaverRepository

class MainViewModel(private val repository: NaverRepository) : ViewModel() {
    val searchText = MutableLiveData<String>()
    val movieList = MutableLiveData<List<Movie>>()
    val msg = MutableLiveData<String>()
    val isVisible = MutableLiveData<Boolean>()
    val keyboard = MutableLiveData<Unit>()
    val showDialog = MutableLiveData<Unit>()
    val hideDialog = MutableLiveData<Unit>()
    val searchTextList = MutableLiveData<ArrayList<QueryHistory>>()

    fun requestMovieInfo() {
        keyboard.value = Unit
        if (searchText.value.isNullOrEmpty()) {
            msg.value = "검색어를 입력해주세요"
        } else {
            isVisible.value = true
            repository.getSearchMovieList(searchText.value!!,
                {
                    isVisible.value = false
                    movieList.value = it.items as ArrayList<Movie>
                    repository.saveMovieData(it.items)
                },
                {
                    isVisible.value = false
                    msg.value = it
                }
            )
        }
    }

    fun showHistory() {
        showDialog.value = Unit
    }

    fun requestLocalMovieData() {
        repository.getMovieData()
            ?.let { movie -> movieList.value = movie }
    }

    fun getRecentTitleList() {
        searchTextList.value = repository.getMovieQueryList() as ArrayList<QueryHistory>?
    }

    fun dialogClose() {
        hideDialog.value = Unit
    }
}