package com.example.data.repository

import com.example.data.model.Movie
import com.example.data.model.NaverMovieResponse
import io.reactivex.Single

interface NaverMoviesRepository {

    fun getNaverMovies(query: String) : Single<NaverMovieResponse>

    fun loadCachedMovies() : Single<List<Movie>>

    fun getCacheKeyword() : String

}