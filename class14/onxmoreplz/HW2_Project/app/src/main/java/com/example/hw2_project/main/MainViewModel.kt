package com.example.hw2_project.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hw2_project.data.repository.MovieRepositoryImpl

class MainViewModel(private val movieRepository: MovieRepositoryImpl) : ViewModel() {
    val query = MutableLiveData<String>()

    private val _movieList: MutableLiveData<List<com.example.hw2_project.data.api.NaverMovieData.NaverMovie>> =
        MutableLiveData()
    val movieList: LiveData<List<com.example.hw2_project.data.api.NaverMovieData.NaverMovie>> = _movieList

    val isEmptyQuery = MutableLiveData<Unit>()
    val successToGetMovie = MutableLiveData<Unit>()
    val failToGetMovie = MutableLiveData<Unit>()
    val startRecentActivity = MutableLiveData<Unit>()

    fun getMovieFromRepository() {
        val queryToString = query.value.toString()
        if (queryToString.isEmpty()) {
            isEmptyQuery.value = Unit
        } else {
            movieRepository.getMovieList(
                queryToString,
                success = {
                    it.items.run {
                        _movieList.value = it.items
                        successToGetMovie.value = Unit
                    }
                },
                fail = {
                    failToGetMovie.value = Unit
                }
            )
        }
    }

    fun clickRecentBtn() {
        startRecentActivity.value = Unit
    }

    class MainViewModelFactory(private val repository: MovieRepositoryImpl) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                MainViewModel(repository) as T
            } else {
                throw IllegalArgumentException()
            }
        }
    }

}