package com.jay.aas.data

import com.jay.aas.model.Movie

interface MovieRemoteDataSource {

    suspend fun getMovies(
        query: String,
        success: (List<Movie>) -> Unit,
        failure: (Throwable) -> Unit,
    )

}