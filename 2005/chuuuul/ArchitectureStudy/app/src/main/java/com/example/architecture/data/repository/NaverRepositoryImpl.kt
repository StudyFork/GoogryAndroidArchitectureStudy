package com.example.architecture.data.repository

import com.example.architecture.data.model.MovieModel
import com.example.architecture.data.source.local.NaverLocalDataSource
import com.example.architecture.data.source.local.NaverLocalDataSourceImpl
import com.example.architecture.data.source.remote.NaverRemoteDataSource
import com.example.architecture.data.source.remote.NaverRemoteDataSourceImpl


class NaverRepositoryImpl(
    private val naverLocalDataSource: NaverLocalDataSource,
    private val naverRemoteDataSource: NaverRemoteDataSource
) : NaverRepository {


    override fun getMovieList(
        keyword: String,
        onSuccess: (movieList: List<MovieModel>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {

        naverLocalDataSource.getMovieList(keyword, onSuccess)

        naverRemoteDataSource.getMovieList(
            keyword,
            {
                onSuccess(it)
                saveMovieList(keyword, it)
            },
            onFailure
        )
    }


    override fun clearCacheData() {
        naverLocalDataSource.clearData()
    }

    override fun saveMovieList(
        keyword: String,
        movieList: List<MovieModel>
    ) {
        naverLocalDataSource.saveMovieList(keyword, movieList)
    }

    companion object {
        private var INSTANCE: NaverRepositoryImpl? = null

        @JvmStatic
        fun getInstance(
            naverLocalDataSource: NaverLocalDataSourceImpl,
            naverRemoteDataSource: NaverRemoteDataSourceImpl
        ): NaverRepositoryImpl {
            return INSTANCE ?: synchronized(NaverRepositoryImpl::class.java) {
                INSTANCE ?: NaverRepositoryImpl(naverLocalDataSource, naverRemoteDataSource)
                    .also { INSTANCE = it }
            }
        }
    }

}