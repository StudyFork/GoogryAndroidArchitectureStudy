package com.example.androidarchitecturestudy.data.repository

import com.example.androidarchitecturestudy.data.local.LocalMovieData
import com.example.androidarchitecturestudy.data.model.Movie
import com.example.androidarchitecturestudy.data.model.MovieData

class RepositoryMovieImpl(
    private val localMovieData: LocalMovieData
) : RepositoryMovie {
    override fun getSearchMovieList(
        query: String,
        success: (MovieData) -> Unit,
        failed: (String) -> Unit
    ) {

    }

    override fun saveMovieData(movie: List<Movie>) {
        localMovieData.saveMovieData(movie)
    }

    override fun getMovieData(): List<Movie>? {
        return localMovieData.getMovieData()
    }

}