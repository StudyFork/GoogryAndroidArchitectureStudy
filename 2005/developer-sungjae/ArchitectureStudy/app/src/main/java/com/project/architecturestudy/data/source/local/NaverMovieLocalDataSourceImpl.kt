package com.project.architecturestudy.data.source.local

import com.project.architecturestudy.data.source.local.room.MovieItemDao
import com.project.architecturestudy.data.source.local.room.MovieLocalItem
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.koin.java.KoinJavaComponent.inject


object NaverMovieLocalDataSourceImpl : NaverMovieLocalDataSource {

    override val movieItemDao: MovieItemDao by inject(MovieItemDao::class.java)

    override fun saveMovieList(data: MovieLocalItem): (Observable<Unit>) = Observable.fromCallable { movieItemDao.insert(data) }

    override fun getMovieList(): Observable<List<MovieLocalItem>> =
        movieItemDao.getMovieList().subscribeOn(Schedulers.io())

    override fun deleteMovieList(): MovieItemDao = movieItemDao
}