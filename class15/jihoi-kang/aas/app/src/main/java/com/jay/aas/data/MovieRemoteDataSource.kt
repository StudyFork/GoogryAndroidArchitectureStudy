package com.jay.aas.data

import com.jay.aas.model.Movie

interface MovieRemoteDataSource {

    suspend fun getSearchMovies(query: String): List<Movie>

}