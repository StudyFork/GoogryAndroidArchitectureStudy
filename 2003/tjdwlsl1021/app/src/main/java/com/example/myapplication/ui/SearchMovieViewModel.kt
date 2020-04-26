package com.example.myapplication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data2.MovieRepository
import com.example.data2.model.Movie
import com.example.myapplication.R

class SearchMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val query = MutableLiveData<String>()
    val movieList = MutableLiveData<List<Movie>>()
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