package com.jay.data.data

import com.jay.data.model.Movie

interface MovieRemoteDataSource {

    suspend fun getSearchMovies(query: String): List<Movie>

}