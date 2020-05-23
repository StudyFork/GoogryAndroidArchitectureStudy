package com.project.architecturestudy.data.source.remote

import com.project.architecturestudy.components.Service
import com.project.architecturestudy.data.model.NaverApiData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NaverMovieRemoteDataSourceImpl : NaverMovieRemoteDataSource {

    override val service = Service.create()
    override val disposable = CompositeDisposable()

    override fun getMovieList(
        keyWord: String,
        Success: (Single<NaverApiData>) -> Unit,
        Failure: (t: Throwable) -> Unit
    ) {
        Success.invoke(
            service.getMovies(keyWord)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
        )
    }
}