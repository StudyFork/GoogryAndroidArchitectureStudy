package com.sangjin.newproject.data.repository

import com.sangjin.newproject.data.model.Movie
import com.sangjin.newproject.data.source.local.LocalDataSource
import com.sangjin.newproject.data.source.local.RoomDB
import com.sangjin.newproject.data.source.remote.RemoteDataSourceImpl

class NaverMoviesRepositoryImpl(
    private val remoteDataSourceImpl: RemoteDataSourceImpl,
    private val localDataSource: LocalDataSource
) : NaverMoviesRepository {

    override fun getNaverMovies(
        query: String,
        roomDB: RoomDB,
        onSuccess: (movies: List<Movie>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {

        localDataSource.getMovieData(query, roomDB,
        onSuccess = { movies ->
            onSuccess(movies)
        })

        remoteDataSourceImpl.getMovieData(query,
            onSuccess = { movies ->
                onSuccess(movies)
                saveMoviesCache(movies, roomDB)
            },
            onFailure = { t ->
                onFailure(t)
            })
    }

    override fun saveMoviesCache(movies: List<Movie>, roomDB: RoomDB) {
            localDataSource.saveMovieData(movies, roomDB)
    }
}