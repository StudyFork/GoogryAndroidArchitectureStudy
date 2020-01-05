package com.onit.googlearchitecturestudy.data.repository

import com.onit.googlearchitecturestudy.Movies
import retrofit2.Response

interface MovieRepository {
    suspend fun getMovieList(keyword: String): Response<Movies>
}