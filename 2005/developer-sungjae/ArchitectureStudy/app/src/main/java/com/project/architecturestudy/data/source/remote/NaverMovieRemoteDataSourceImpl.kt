package com.project.architecturestudy.data.source.remote

import com.project.architecturestudy.components.RetrofitService
import com.project.architecturestudy.data.model.Movie
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class NaverMovieRemoteDataSourceImpl : NaverMovieRemoteDataSource {

    override val service = RetrofitService.create()
    override val disposable = CompositeDisposable()

    override fun getMovieList(
        keyWord: String,
        Success: (Single<Movie>) -> Unit,
        Failure: (t: Throwable) -> Unit
    ) {
        Success.invoke(service.getMovies(keyWord))
    }
}