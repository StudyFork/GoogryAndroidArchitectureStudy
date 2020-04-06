package com.example.myapplication.ui

import androidx.databinding.ObservableField
import com.example.myapplication.R
import com.example.myapplication.data.local.MovieEntity
import com.example.myapplication.data.repository.MovieRepository

class SearchMovieViewModel(private val movieRepository: MovieRepository) {
    val query = ObservableField<String>()
    val movieList = ObservableField<List<MovieEntity>>()
    val failMsg = ObservableField<Pair<Boolean, Any>>()

    fun searchMovie() {
        if (query.get().isNullOrEmpty()) {
            failMsg.set(Pair(false, R.string.activity_toast_empty_movie_title))
        } else {
            movieRepository.getMovieList(
                query.get().toString(),
                success = {
                    movieList.set(it)
                },
                failed = {
                    failMsg.set(Pair(false, it.toString()))
                })
        }
    }
}