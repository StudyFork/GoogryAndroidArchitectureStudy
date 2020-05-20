package com.sangjin.newproject.data.repository

import com.sangjin.newproject.data.model.Movie
import com.sangjin.newproject.data.model.NaverMovieResponse
import com.sangjin.newproject.data.source.local.LocalDataSourceImpl
import com.sangjin.newproject.data.source.local.RoomDB
import com.sangjin.newproject.data.source.remote.RemoteDataSourceImpl
import io.reactivex.Single

class NaverMoviesRepositoryImpl(
    private val remoteDataSourceImpl: RemoteDataSourceImpl,
    private val localDataSourceImpl: LocalDataSourceImpl
) : NaverMoviesRepository {

    override fun getNaverMovies(query: String): Single<NaverMovieResponse> {
        return remoteDataSourceImpl.getMovieData(query)
            .doOnSuccess{ naverMovieResponse ->
                saveMoviesCache(naverMovieResponse.items)
            }
    }

    private fun saveMoviesCache(movies: List<Movie>) {
        localDataSourceImpl.saveMovieData(movies)
    }

    override fun loadCachedMovies(): Single<List<Movie>> = localDataSourceImpl.getMovieData()

}