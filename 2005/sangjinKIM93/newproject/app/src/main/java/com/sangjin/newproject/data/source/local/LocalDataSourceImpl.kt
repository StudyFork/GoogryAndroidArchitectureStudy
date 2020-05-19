package com.sangjin.newproject.data.source.local

import android.annotation.SuppressLint
import com.sangjin.newproject.data.model.Movie
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LocalDataSourceImpl(room : RoomDB) : LocalDataSource {

    private val roomDB = room

    @SuppressLint("CheckResult")
    override fun getMovieData(
        query: String,
        onSuccess: (movies: List<Movie>) -> Unit
    ) {

        roomDB.movieDao.selectMovies("%${query}%")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { movies ->
                if(!movies.isNullOrEmpty()){
                    onSuccess(movies)
                }
            }
    }

    override fun saveMovieData(movies: List<Movie>) {

        Completable.fromRunnable {
            Runnable {
                roomDB.movieDao.insertAll(movies)
            }.run()
        }.subscribeOn(Schedulers.io())
            .subscribe()
    }
}