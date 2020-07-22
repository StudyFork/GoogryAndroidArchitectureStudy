package com.lllccww.data.repository

import com.lllccww.data.model.MovieItem
import com.lllccww.data.source.remote.MovieRemoteDataSource

internal class NaverMovieRepositoryImpl(private val movieRemoteDataSource: MovieRemoteDataSource) : NaverMovieRepository {

    //private val movieRemoteDataSourceImpl = MovieRemoteDataSourceImpl()
    override fun getSearchMovie(
        keyWord: String,
        success: (List<MovieItem>) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        movieRemoteDataSource.getMovieList(
            keyWord,
            success,
            failure
        )
    }
}