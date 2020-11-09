package com.example.studyfork.model

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {
    private val compositeDisposable = CompositeDisposable()

    override fun searchMovie(query: String, success: () -> Unit, fail: () -> Unit) {
        remoteDataSource.searchMovie(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                success
            }, {
                fail
                it.printStackTrace()
            })
            .addTo(compositeDisposable)
    }
}