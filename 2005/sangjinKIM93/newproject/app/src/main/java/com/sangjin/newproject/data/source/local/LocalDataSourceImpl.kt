package com.sangjin.newproject.data.source.local

import android.annotation.SuppressLint
import com.sangjin.newproject.data.model.Movie
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LocalDataSourceImpl(private val roomDB: RoomDB) : LocalDataSource {

    override fun getMovieData() : Single<List<Movie>> =  roomDB.movieDao.getAllMovies()

    override fun saveMovieData(movies: List<Movie>) {

        Completable.fromRunnable {
            Runnable {
                roomDB.movieDao.deleteAll()
                roomDB.movieDao.insertAll(movies)
            }.run()
        }.subscribeOn(Schedulers.io())
            .subscribe()
    }
}