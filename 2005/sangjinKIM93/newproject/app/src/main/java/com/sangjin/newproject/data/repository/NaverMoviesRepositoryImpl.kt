package com.sangjin.newproject.data.repository

import android.util.Log
import com.sangjin.newproject.data.model.Movie
import com.sangjin.newproject.data.model.NaverMovieResponse
import com.sangjin.newproject.data.source.local.LocalDataSource
import com.sangjin.newproject.data.source.remote.RemoteDataSource
import io.reactivex.Single

class NaverMoviesRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : NaverMoviesRepository {

    override fun getNaverMovies(query: String): Single<NaverMovieResponse> {
        return remoteDataSource.getMovieData(query)
            .doOnSuccess{ naverMovieResponse ->
                saveMoviesCache(naverMovieResponse.items, query)
            }
    }

    private fun saveMoviesCache(movies: List<Movie>, query: String) {
        localDataSource.saveMovieData(movies)

        if(movies.isNullOrEmpty()){
            localDataSource.clearCacheKeyword()
        } else {
            localDataSource.saveCacheKeyword(query)
        }
    }

    override fun loadCachedMovies(): Single<List<Movie>> = localDataSource.getMovieData()

    override fun getCacheKeyword(): String = localDataSource.getCacheKeyword()

}