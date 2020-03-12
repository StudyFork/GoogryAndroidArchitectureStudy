package com.byiryu.study.model

import android.content.Context
import com.byiryu.study.model.data.MovieItem
import com.byiryu.study.model.local.LocalDataBase
import com.byiryu.study.model.local.LocalDataSource
import com.byiryu.study.model.local.MovieDao
import com.byiryu.study.model.remote.RemoteDataSource
import io.reactivex.Single

class Repository constructor(context: Context) {

    private var remoteDataSource: RemoteDataSource = RemoteDataSource()
    private var movieDao: MovieDao = LocalDataBase.getInstance(context).movieDao()
    private var localDataSource: LocalDataSource = LocalDataSource(movieDao)

    fun getMovieList(
        query: String
    ): Single<List<MovieItem>> {
        return localDataSource.getAll()
            .flatMap { movies ->
                if (movies.isEmpty()) {
                    getMovieListWithRemote(query)
                } else {
                    Single.just(movies)
                }
            }
    }

    //추가
    private fun getMovieListWithRemote(query: String): Single<List<MovieItem>> {
        return remoteDataSource.getMoveList(query)
            .map { it.items }
            .flatMap { movies ->
                localDataSource.saveMovies(movies)
                    .andThen(Single.just(movies))
            }
    }

}