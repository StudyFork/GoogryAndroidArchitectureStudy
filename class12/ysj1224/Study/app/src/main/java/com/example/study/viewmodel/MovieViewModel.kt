package com.example.study.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.study.data.model.NaverApiData
import com.example.study.data.repository.MovieListRepositoryImpl

class MovieViewModel : ViewModel() {
    private val repository = MovieListRepositoryImpl()

    private val _movieList = MutableLiveData<List<NaverApiData.Item>>()
    val movieList: LiveData<List<NaverApiData.Item>> get() = _movieList
    private val _fail = MutableLiveData<Throwable>()
    val fail: LiveData<Throwable> get() = _fail
    private val _noQuery = MutableLiveData<Boolean>(false)
    val noQuery: LiveData<Boolean> get() = _noQuery

    val searchString = MutableLiveData<String>()


    fun requestMovieList() {
        if (searchString.value.isNullOrEmpty()) {
            showQueryEmpty()
        } else {
            doSearch(searchString.value.toString())
        }
    }

    private fun showQueryEmpty() {
        _noQuery.value = !(_noQuery.value)!!
    }

    private fun doSearch(query: String) {
        repository.doSearch(
            query = query,
            response = { _movieList.value = it },
            fail = { _fail.value = it })
    }
}
