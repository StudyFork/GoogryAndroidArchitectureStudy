package io.github.jesterz91.study.data.local.source

import io.github.jesterz91.study.data.local.dao.MovieDao
import io.github.jesterz91.study.data.local.model.MovieLocal
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class MovieLocalDataSourceImpl(private val movieDao: MovieDao) : MovieLocalDataSource {

    override fun selectMovie(): Single<List<MovieLocal>> {
        return Single.just(movieDao.selectMovie())
            .subscribeOn(Schedulers.io())
    }
}