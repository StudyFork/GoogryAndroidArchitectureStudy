package com.project.architecturestudy.data.repository

import com.project.architecturestudy.data.model.Movie
import com.project.architecturestudy.data.source.local.NaverMovieLocalDataSource
import com.project.architecturestudy.data.source.local.room.MovieLocal
import com.project.architecturestudy.data.source.remote.NaverMovieRemoteDataSource

class NaverMovieRepositoryImpl(
    private val naverMovieLocalDataSource: NaverMovieLocalDataSource,
    private val naverMovieRemoteDataSource: NaverMovieRemoteDataSource
) : NaverMovieRepository {

    override fun getCashedMovieList(
        Success: (ArrayList<MovieLocal>) -> Unit,
        Failure: (t: Throwable) -> Unit
    ) {
        naverMovieLocalDataSource.getMovieList(Success, Failure)
    }

    override fun getMovieList(
        keyWord: String,
        Success: (ArrayList<Movie.Items>) -> Unit,
        Failure: (t: Throwable) -> Unit
    ) {
        naverMovieRemoteDataSource.getMovieList(keyWord,
            Success = {
                Success.invoke(it)
                naverMovieLocalDataSource.saveMovieList(it)

            },
            Failure = {
                Failure.invoke(it)
            }
        )
    }

    override fun dispose() {
        naverMovieLocalDataSource.dispose()
        naverMovieRemoteDataSource.dispose()
    }
}