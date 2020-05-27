package com.olaf.nukeolaf.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.olaf.nukeolaf.data.repository.MovieRepository

class MainViewModelFactory(private val movieRepository: MovieRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(movieRepository) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}