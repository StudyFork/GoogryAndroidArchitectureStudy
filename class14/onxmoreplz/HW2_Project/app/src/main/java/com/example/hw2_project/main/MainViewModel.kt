package com.example.hw2_project.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hw2_project.data.repository.MovieRepository

class MainViewModel @ViewModelInject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
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

}