package com.architecture.androidarchitecturestudy.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.architecture.androidarchitecturestudy.data.model.Movie
import com.architecture.androidarchitecturestudy.data.repository.MovieRepository
import com.architecture.androidarchitecturestudy.util.SingleLiveEvent

class MainViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    val keyword = MutableLiveData<String>()
    val movieList = MutableLiveData<List<Movie>>()
    val msg = SingleLiveEvent<String>()
    val callSearchHistory = MutableLiveData<Unit>()

    fun findMovie() {
        val keywordValue = keyword.value ?: return
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