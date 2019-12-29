package com.onit.googlearchitecturestudy.data.repository

import com.onit.googlearchitecturestudy.Movies
import retrofit2.Response

interface MovieRepository {
    fun getMovieList(): Response<Movies>
}