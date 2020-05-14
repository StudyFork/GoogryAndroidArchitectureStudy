package com.example.studyforkandroid.viewmodel

import androidx.databinding.ObservableField
import com.example.studyforkandroid.data.Movie
import com.example.studyforkandroid.data.source.MovieRepositoryImpl

class MovieViewModel(
    private val movieRepository: MovieRepositoryImpl
) {
    val movies = ObservableField<List<Movie>>()
    val query = ObservableField<String>()

    fun searchMovies() {
        val searchQuery = query.get() as String
        movieRepository.getRemoteMovieList(
            searchQuery
            , onSuccess = { movieList: List<Movie> ->
                movies.set(movieList)
            },
            onFailure = { e ->
                e.printStackTrace()
            })
    }

}