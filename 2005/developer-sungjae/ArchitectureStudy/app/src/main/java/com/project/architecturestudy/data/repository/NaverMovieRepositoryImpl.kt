package com.project.architecturestudy.data.repository

import com.project.architecturestudy.data.model.Movie
import com.project.architecturestudy.data.source.local.NaverMovieLocalDataSource
import com.project.architecturestudy.data.source.remote.NaverMovieRemoteDataSource

class NaverMovieRepositoryImpl(
    val naverMovieLocalDataSource: NaverMovieLocalDataSource,
    val naverMovieRemoteDataSource: NaverMovieRemoteDataSource

) :
    NaverMovieRepository {
    override fun getMovieList(
        query: String,
        Success: (Movie.Items) -> Unit,
        Failure: (Throwable) -> Unit
    ) {

    }

}