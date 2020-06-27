package com.lllccww.studyforkproject.data.repository

import com.lllccww.studyforkproject.data.model.MovieItem
import com.lllccww.studyforkproject.data.source.remote.MovieRemoteDataSource

class NaverMovieRepositoryImpl(private val movieRemoteDataSource: MovieRemoteDataSource) : NaverMovieRepository {

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