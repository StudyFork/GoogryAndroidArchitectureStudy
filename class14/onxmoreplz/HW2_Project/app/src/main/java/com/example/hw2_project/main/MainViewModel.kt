package com.example.hw2_project.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hw2_project.data.repository.MovieRepositoryImpl

class MainViewModel(private val movieRepository: MovieRepositoryImpl) : ViewModel() {
    val query = MutableLiveData<String>()

    val movieList: MutableLiveData<List<com.example.hw2_project.data.api.NaverMovieData.NaverMovie>> =
        MutableLiveData()

    val isEmptyQuery = MutableLiveData<Unit>()
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
                        movieList.value = it.items
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