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
        onGetRemoteData: (Single<NaverApiData>) -> Unit
    ) {
        naverMovieRemoteDataSource.getMovieList(keyWord,
            onGetRemoteData = {
                onGetRemoteData.invoke(it)
                naverMovieLocalDataSource.saveMovieList(it)
            }
        )
    }

    override fun dispose() {
        naverMovieLocalDataSource.dispose()
    }
}