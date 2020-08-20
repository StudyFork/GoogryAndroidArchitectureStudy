package com.example.study.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.study.data.model.NaverApiData
import com.example.study.data.repository.MovieListRepository
import com.example.study.data.repository.MovieListRepositoryImpl

class MovieViewModel(private val repository: MovieListRepository) : ViewModel() {

    private val _movieList = MutableLiveData<List<NaverApiData.Item>>()
    val movieList: LiveData<List<NaverApiData.Item>> get() = _movieList
    private val _fail = MutableLiveData<Throwable>()
    val fail: LiveData<Throwable> get() = _fail
    val searchString = MutableLiveData<String>()
    val noQuery = MutableLiveData<Unit>()


    fun requestMovieList() {
        if (searchString.value.isNullOrEmpty()) {
            showQueryEmpty()
        } else {
            doSearch(searchString.value.toString())
        }
    }

     fun showQueryEmpty() {
         noQuery.value = Unit
    }

     fun doSearch(query: String) {
        repository.doSearch(
            query = query,
            response = { _movieList.value = it },
            fail = { _fail.value = it })
    }
}
