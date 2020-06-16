package com.project.architecturestudy.data.repository

import com.project.architecturestudy.data.model.NaverApiData
import com.project.architecturestudy.data.source.local.NaverMovieLocalDataSource
import com.project.architecturestudy.data.source.local.room.MovieLocalItem
import com.project.architecturestudy.data.source.remote.NaverMovieRemoteDataSource
import io.reactivex.Observable
import io.reactivex.Single

class NaverMovieRepositoryImpl(
    private val naverMovieLocalDataSource: NaverMovieLocalDataSource,
    private val naverMovieRemoteDataSource: NaverMovieRemoteDataSource
) : NaverMovieRepository {

    override fun getCashedMovieList(
        onSuccess: (Observable<List<MovieLocalItem>>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {
        naverMovieLocalDataSource.getMovieList(onSuccess, onFailure)
    }

    override fun getMovieList(
        keyWord: String,
        onGetRemoteData: (Single<NaverApiData>) -> Unit
    ) {
        naverMovieRemoteDataSource.getMovieList(keyWord, onGetRemoteData)
    }

    companion object {

        private var INSTANCE: NaverMovieRepositoryImpl? = null

        fun getInstance(
            naverMovieLocalDataSource: NaverMovieLocalDataSource,
            naverMovieRemoteDataSource: NaverMovieRemoteDataSource
        ): NaverMovieRepositoryImpl {
            return INSTANCE ?: NaverMovieRepositoryImpl(naverMovieLocalDataSource, naverMovieRemoteDataSource)
                .apply { INSTANCE = this }
        }

    }

}