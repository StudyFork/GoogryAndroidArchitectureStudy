package com.showmiso.architecturestudy.data.repository

import com.showmiso.architecturestudy.api.MovieModel
import com.showmiso.architecturestudy.data.remote.RemoteDataSource
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class NaverRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : NaverRepository {

    override fun getMoviesList(query: String): Single<List<MovieModel.Movie>> {
        return remoteDataSource.getMovies(query)
            .subscribeOn(Schedulers.io())
            .map {
                it.items ?: listOf()
            }
    }

    override fun addHistory(query: String) {
    }

    override fun getHistory(): List<String> {
        return listOf()
    }

    override fun removeHistory(query: String) {
    }
}
