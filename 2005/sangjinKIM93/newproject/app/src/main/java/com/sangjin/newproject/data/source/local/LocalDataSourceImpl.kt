package com.sangjin.newproject.data.source.local

import com.sangjin.newproject.data.model.Movie
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class LocalDataSourceImpl(private val roomDb: RoomDb) : LocalDataSource {

    override fun getMovieData() : Single<List<Movie>> =  roomDb.movieDao.getAllMovies()

    override fun saveMovieData(movies: List<Movie>) {

        Completable.fromRunnable {
            Runnable {
                roomDb.movieDao.deleteAll()
                roomDb.movieDao.insertAll(movies)
            }.run()
        }.subscribeOn(Schedulers.io())
            .subscribe()
    }
}