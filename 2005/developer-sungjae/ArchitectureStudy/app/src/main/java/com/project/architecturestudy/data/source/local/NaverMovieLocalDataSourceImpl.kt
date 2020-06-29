package com.project.architecturestudy.data.source.local

import com.project.architecturestudy.data.source.local.room.MovieItemDao
import com.project.architecturestudy.data.source.local.room.MovieLocalItem
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class NaverMovieLocalDataSourceImpl(private val movieItemDao: MovieItemDao) : NaverMovieLocalDataSource {


    override fun saveMovieList(data: MovieLocalItem): (Observable<Unit>) = Observable.fromCallable { movieItemDao.insert(data) }

    override fun getMovieList(): Observable<List<MovieLocalItem>> =
        movieItemDao.getMovieList().subscribeOn(Schedulers.io())

    override fun deleteMovieList(): MovieItemDao = movieItemDao
}