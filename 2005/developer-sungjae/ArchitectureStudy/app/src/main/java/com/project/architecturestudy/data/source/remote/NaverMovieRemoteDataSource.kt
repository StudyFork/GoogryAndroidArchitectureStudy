package com.project.architecturestudy.data.source.remote

import com.project.architecturestudy.components.RetrofitService
import com.project.architecturestudy.data.model.Movie
import io.reactivex.disposables.CompositeDisposable

interface NaverMovieRemoteDataSource {

    val service: RetrofitService
    val disposable: CompositeDisposable
    fun getMovieList(
        keyWord: String,
        Success: (ArrayList<Movie.Items>) -> Unit,
        Failure: (t : Throwable) -> Unit
    )

    fun dispose()
}