package com.project.local.impl

import com.project.data.NaverMovieLocalDataSource
import com.project.data.model.NaverMovieEntity
import com.project.local.MovieItemDao
import com.project.local.model.MovieLocalItem
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers


class NaverMovieLocalDataSourceImpl(private val movieItemDao: MovieItemDao) : NaverMovieLocalDataSource {


    override fun saveMovieList(data: NaverMovieEntity): Completable {
        return movieItemDao.insert(
            MovieLocalItem(
                id = System.currentTimeMillis(),
                list = data.items
            )
        )
    }


    override fun getMovieList(): Single<NaverMovieEntity> =
        movieItemDao
            .getMovieList().map { NaverMovieEntity(items = it.list) }
            .toSingle()
            .subscribeOn(Schedulers.io())

    override fun deleteMovieList() {
        movieItemDao.deleteAll()
    }
}