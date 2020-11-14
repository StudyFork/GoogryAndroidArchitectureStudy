package com.wybh.androidarchitecturestudy.model.remote

import com.wybh.androidarchitecturestudy.data.ResponseCinemaData
import com.wybh.androidarchitecturestudy.retrofit.RetrofitCreator
import com.wybh.androidarchitecturestudy.retrofit.RetrofitImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NaverRemoteDataSourceImpl : NaverRemoteDataSource {
    private val compositeDisposable = CompositeDisposable()

    override fun searchCinema(
        query: String,
        success: (ResponseCinemaData) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        compositeDisposable.add(
            RetrofitCreator.create(RetrofitImpl::class.java)
                .getCinemaData(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe({ response: ResponseCinemaData ->
                    success(response)

                }, { error: Throwable ->
                    fail(error)
                })
        )
    }
}