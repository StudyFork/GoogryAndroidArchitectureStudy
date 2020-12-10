package com.example.androidarchitecturestudy.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidarchitecturestudy.data.model.Movie
import com.example.androidarchitecturestudy.data.model.QueryHistory
import com.example.androidarchitecturestudy.data.repository.NaverRepository

class MainViewModel(private val repository: NaverRepository) : ViewModel() {
    val searchText = MutableLiveData<String>()
    val movieList = MutableLiveData<List<Movie>>()

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String>
        get() = _msg

    val isVisible = MutableLiveData<Boolean>()

    private val _keyboard = MutableLiveData<Unit>()
    val keyboard: LiveData<Unit>
        get() = _keyboard

    private val _showDialog = MutableLiveData<Unit>()
    val showDialog: LiveData<Unit>
        get() = _showDialog

    private val _hideDialog = MutableLiveData<Unit>()
    val hideDialog: LiveData<Unit>
        get() = _hideDialog

    val searchTextList = MutableLiveData<ArrayList<QueryHistory>>()

    fun requestMovieInfo() {
        _keyboard.value = Unit
        if (searchText.value.isNullOrEmpty()) {
            _msg.value = "검색어를 입력해주세요"
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
                    _msg.value = it
                }
            )
        }
    }

    fun showHistory() {
        _showDialog.value = Unit
    }

    fun requestLocalMovieData() {
        repository.getMovieData()
            ?.let { movie -> movieList.value = movie }
    }

    fun getRecentTitleList() {
        searchTextList.value = repository.getMovieQueryList() as ArrayList<QueryHistory>?
    }

    fun dialogClose() {
        _hideDialog.value = Unit
    }
}