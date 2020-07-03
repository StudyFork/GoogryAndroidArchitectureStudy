package com.example.data.repository

import com.example.data.model.Movie
import com.example.data.model.NaverMovieResponse
import com.example.data.source.local.LocalDataSource
import com.example.data.source.remote.RemoteDataSource
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NaverMoviesRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : NaverMoviesRepository {

    override fun getNaverMovies(query: String): Single<NaverMovieResponse> {
        return remoteDataSource.getMovieData(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
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