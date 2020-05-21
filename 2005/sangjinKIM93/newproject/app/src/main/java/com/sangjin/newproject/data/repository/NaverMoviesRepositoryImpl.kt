package com.sangjin.newproject.data.repository

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
                saveMoviesCache(naverMovieResponse.items)
            }
    }

    private fun saveMoviesCache(movies: List<Movie>) {
        localDataSource.saveMovieData(movies)
    }

    override fun loadCachedMovies(): Single<List<Movie>> = localDataSource.getMovieData()

}