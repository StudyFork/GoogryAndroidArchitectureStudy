package com.example.studyforkandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studyforkandroid.data.Movie
import com.example.studyforkandroid.data.source.MovieRepositoryImpl
import org.koin.core.KoinComponent

class MovieViewModel(
    private val movieRepository: MovieRepositoryImpl
) : ViewModel(), KoinComponent {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    val query = MutableLiveData<String>()

    fun searchMovies() {
        val searchQuery = query.value ?: return
        movieRepository.getRemoteMovieList(
            searchQuery
            , onSuccess = { movieList: List<Movie> ->
                _movies.postValue(movieList)
            },
            onFailure = { e ->
                e.printStackTrace()
            })
    }
}