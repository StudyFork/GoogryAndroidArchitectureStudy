package com.architecture.androidarchitecturestudy.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.architecture.androidarchitecturestudy.data.model.Movie
import com.architecture.androidarchitecturestudy.data.repository.MovieRepository

class MainViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    val keyword = MutableLiveData<String>()
    val movieList = MutableLiveData<List<Movie>>()
    val msg = MutableLiveData<String>()
    val callSearchHistory = MutableLiveData<Unit>()
    var isVisibleToast = true


    fun findMovie() {
        val keywordValue = keyword.value ?: return
        isVisibleToast = true
        if (keywordValue.isNullOrBlank()) {
            msg.value = "emptyKeyword"
            return
        }
        movieRepository.getMovieData(keyword = keywordValue, 30,
                onSuccess = {
                    if (it.items!!.isEmpty()) {
                        msg.value = "emptyResult"
                    } else {
                        msg.value = "success"
                        movieList.value = it.items
                    }
                },
                onFailure = {
                    msg.value = "fail"
                }
        )
    }

    fun searchHistory() {
        callSearchHistory.value = Unit
    }
}