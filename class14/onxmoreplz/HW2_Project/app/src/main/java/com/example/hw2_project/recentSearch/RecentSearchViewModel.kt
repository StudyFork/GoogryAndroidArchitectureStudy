package com.example.hw2_project.recentSearch

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hw2_project.data.repository.MovieRepositoryImpl

class RecentSearchViewModel @ViewModelInject constructor(
    private val movieRepository: MovieRepositoryImpl
) : ViewModel() {
    val movieListFromSharedPref = MutableLiveData<List<String>>()
    val backToMainActivity = MutableLiveData<Unit>()

    fun getRecentMovie() {
        movieListFromSharedPref.value = movieRepository.getSavedQuery()
    }

    fun clickBackBtn() {
        backToMainActivity.value = Unit
    }
}