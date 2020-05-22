package com.project.architecturestudy.data.source.remote

import com.project.architecturestudy.components.RetrofitService
import com.project.architecturestudy.data.model.Movie
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

interface NaverMovieRemoteDataSource {

    val service: RetrofitService
    val disposable: CompositeDisposable
    fun getMovieList(
        keyWord: String,
        Success: (Single<Movie>) -> Unit,
        Failure: (t: Throwable) -> Unit
    )
}