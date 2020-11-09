package com.example.androidarchitecturestudy.data.repository

import com.example.androidarchitecturestudy.data.local.LocalMovieData
import com.example.androidarchitecturestudy.data.model.Movie
import com.example.androidarchitecturestudy.data.model.MovieData
import com.example.androidarchitecturestudy.data.remote.RemoteMovieData

class RepositoryMovieImpl(
    private val remoteMovieData: RemoteMovieData,
    private val localMovieData: LocalMovieData
) : RepositoryMovie {
    override fun getSearchMovieList(
        query: String,
        success: (MovieData) -> Unit,
        failed: (String) -> Unit
    ) {
        remoteMovieData.getSearchMovieList(query, success, failed)
    }

    override fun saveMovieData(movie: List<Movie>) {
        localMovieData.saveMovieData(movie)
    }

    override fun getMovieData(): List<Movie>? {
        return localMovieData.getMovieData()
    }

}