package com.lllccww.studyforkproject.data.repository

import com.lllccww.studyforkproject.data.model.MovieItem
import com.lllccww.studyforkproject.data.source.remote.MovieRemoteDataSourceImpl

class NaverMovieRepositoryImpl(private val movieRepositoryImpl: MovieRemoteDataSourceImpl) : NaverMovieRepository {

    override fun getSearchMovie(
        keyWord: String,
        success: (List<MovieItem>) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        movieRepositoryImpl.getMovieList(
            keyWord,
            success,
            failure
        )
    }
}