package com.project.architecturestudy.data.repository

import com.project.architecturestudy.data.model.MovieItem
import com.project.architecturestudy.data.model.NaverApiData
import com.project.architecturestudy.data.source.local.NaverMovieLocalDataSource
import com.project.architecturestudy.data.source.remote.NaverMovieRemoteDataSource
import io.reactivex.Single

class NaverMovieRepositoryImpl(
    private val naverMovieLocalDataSource: NaverMovieLocalDataSource,
    private val naverMovieRemoteDataSource: NaverMovieRemoteDataSource
) : NaverMovieRepository {

    override fun getCashedMovieList(
        Success: (ArrayList<MovieItem>) -> Unit,
        Failure: (t: Throwable) -> Unit
    ) {
        naverMovieLocalDataSource.getMovieList(Success, Failure)
    }

    override fun getMovieList(
        keyWord: String,
        Success: (Single<NaverApiData>) -> Unit,
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
    }
}