package com.example.androidarchitecturestudy.data.repository

import com.example.androidarchitecturestudy.data.local.NaverLocalDataSource
import com.example.androidarchitecturestudy.data.model.Movie
import com.example.androidarchitecturestudy.data.model.MovieData
import com.example.androidarchitecturestudy.data.remote.NaverRemoteDataSource

class NaverRepositoryImpl(
    private val naverRemoteDataSource: NaverRemoteDataSource,
    private val naverLocalDataSource: NaverLocalDataSource
) : NaverRepository {
    override fun getSearchMovieList(
        query: String,
        success: (MovieData) -> Unit,
        failed: (String) -> Unit
    ) {
        naverRemoteDataSource.getSearchMovieList(query, success, failed)
    }

    override fun saveMovieData(movie: List<Movie>) {
        naverLocalDataSource.saveMovieData(movie)
    }

    override fun getMovieData(): List<Movie>? {
        return naverLocalDataSource.getMovieData()
    }

}