package com.example.hw2_project.recentSearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hw2_project.data.repository.MovieRepositoryImpl

class RecentSearchViewModel(private val repository: MovieRepositoryImpl): ViewModel() {
    val movieListFromSharedPref = MutableLiveData<List<String>>()
    val backToMainActivity = MutableLiveData<Unit>()

    fun getRecentMovie() {
        movieListFromSharedPref.value = repository.getSavedQuery()
    }

    fun clickBackBtn() {
        backToMainActivity.value = Unit
    }

    class RecentSearchViewModelFactory(private val repository: MovieRepositoryImpl) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(RecentSearchViewModel::class.java)) {
                RecentSearchViewModel(repository) as T
            } else {
                throw IllegalArgumentException()
            }
        }
    }
}