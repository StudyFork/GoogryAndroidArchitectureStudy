package com.example.myapplication.ui

import androidx.databinding.ObservableField
import com.example.myapplication.R
import com.example.myapplication.data.local.MovieEntity
import com.example.myapplication.data.repository.MovieRepository

class SearchMovieViewModel(private val movieRepository: MovieRepository) {
    var query = ObservableField<String>()
    var movieList = ObservableField<ArrayList<MovieEntity>>()
    var emptyMsg = ObservableField<Pair<Boolean, Any>>()
    var failMsg = ObservableField<Pair<Boolean, Any>>()

    fun searchMovie() {
        if (query.get().isNullOrEmpty()) {
            emptyMsg.set(Pair(false, R.string.activity_toast_empty_movie_title))
        } else {
            movieRepository.getMovieList(
                query.get().toString(),
                success = {
                    movieList.set(it as ArrayList<MovieEntity>)
                },
                failed = {
                    failMsg.set(Pair(false, it.toString()))
                })
        }
    }
}