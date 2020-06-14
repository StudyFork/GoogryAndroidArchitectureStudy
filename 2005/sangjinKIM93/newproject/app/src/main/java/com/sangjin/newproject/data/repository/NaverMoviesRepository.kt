package com.sangjin.newproject.data.repository

import com.sangjin.newproject.data.model.Movie
import com.sangjin.newproject.data.model.NaverMovieResponse
import io.reactivex.Single

interface NaverMoviesRepository {

    fun getNaverMovies(query: String) : Single<NaverMovieResponse>

    fun loadCachedMovies() : Single<List<Movie>>

    fun getCacheKeyword() : String

}