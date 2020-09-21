package com.example.dkarch.domain.repository

import com.example.dkarch.data.response.MovieResponse
import io.reactivex.Single
import retrofit2.Response

interface NaverMovieRepository {
    fun getMovies(query: String): Single<Response<MovieResponse>>

    fun saveQuery(query: String)

    fun getQueryList(): List<String>
}
