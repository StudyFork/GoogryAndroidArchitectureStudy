package com.example.myapplication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.R
import com.example.myapplication.data.local.MovieEntity
import com.example.myapplication.data.repository.MovieRepository

class SearchMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val query = MutableLiveData<String>()
    val movieList = MutableLiveData<List<MovieEntity>>()
    val failMsg = MutableLiveData<Pair<Boolean, Any>>()

    fun searchMovie() {

        if (query.value.isNullOrEmpty()) {
            failMsg.value = (Pair(false, R.string.activity_toast_empty_movie_title))
        } else {
            movieRepository.getMovieList(
                query.value.toString(),
                success = {
                    movieList.value = it
                },
                failed = {
                    failMsg.value = (Pair(false, it.toString()))
                })
        }
    }
}