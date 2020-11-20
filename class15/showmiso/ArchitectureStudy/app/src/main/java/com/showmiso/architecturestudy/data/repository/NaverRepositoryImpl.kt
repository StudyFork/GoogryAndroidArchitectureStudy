package com.showmiso.architecturestudy.data.repository

import com.showmiso.architecturestudy.api.MovieModel
import com.showmiso.architecturestudy.data.remote.RemoteDataSource
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class NaverRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : NaverRepository {

    override fun getMovies(query: String): Single<MovieModel.MovieResponse> {
        return remoteDataSource.getMovies(query)
            .subscribeOn(Schedulers.io())
    }

    override fun getMoviesList(query: String): Single<List<MovieModel.Movie>> {
        return remoteDataSource.getMovies(query)
            .subscribeOn(Schedulers.io())
            .map {
                it.items ?: listOf()
            }
    }
}
