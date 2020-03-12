package com.byiryu.study.model.source.local

import com.byiryu.study.model.data.MovieItem
import io.reactivex.Completable
import io.reactivex.Single

class LocalDataSource constructor(private val movieDao: MovieDao) {

    fun getAll(): Single<List<MovieItem>> {
        return movieDao.getAll()
    }

    fun saveMovies(movies: List<MovieItem>): Completable {
        return movieDao.deleteAll()
            .doOnComplete {
                movieDao.insertAll(movies)
            }


    }
}