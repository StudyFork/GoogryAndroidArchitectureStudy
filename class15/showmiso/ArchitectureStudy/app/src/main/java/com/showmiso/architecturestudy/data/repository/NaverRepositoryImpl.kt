package com.showmiso.architecturestudy.data.repository

import com.showmiso.architecturestudy.api.MovieModel
import com.showmiso.architecturestudy.data.remote.RemoteDataSource
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NaverRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : NaverRepository {

    override fun getMovies(query: String): Single<MovieModel.MovieResponse> {
        return remoteDataSource.getMovies(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}
