package com.example.googryandroidarchitecturestudy.data.repository

import com.example.googryandroidarchitecturestudy.domain.Movie

interface MovieRepository {
    suspend fun searchMovies(search: String): List<Movie>
}