package com.sangjin.newproject.data.source.local

import android.annotation.SuppressLint
import com.sangjin.newproject.data.model.Movie
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LocalDataSourceImpl : LocalDataSource {

    @SuppressLint("CheckResult")
    override fun getMovieData(
        query: String,
        roomDB: RoomDB,
        onSuccess: (movies: List<Movie>) -> Unit
    ) {

        roomDB.movieDao.selectMovies("%${query}%")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movies ->
                onSuccess(movies)
            })
    }

    override fun saveMovieData(movies: List<Movie>, roomDB: RoomDB) {

        Completable.fromRunnable {
            Runnable {
                roomDB.movieDao.insertAll(movies)
            }.run()
        }.subscribeOn(Schedulers.io())
            .subscribe()
    }
}