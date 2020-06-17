package com.lllccww.studyforkproject.data.repository

import com.lllccww.studyforkproject.data.model.MovieItem
import com.lllccww.studyforkproject.data.source.remote.MovieRemoteDataSourceImpl

class NaverMovieRepositoryImpl : NaverMovieRepository {

    private val movieRemoteDataSourceImpl = MovieRemoteDataSourceImpl()
    override fun getSearchMovie(
        keyWord: String,
        success: (List<MovieItem>) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        movieRemoteDataSourceImpl.getMovieList(
            keyWord,
            success,
            failure
        )
    }
}