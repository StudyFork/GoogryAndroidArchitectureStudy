package com.example.studyfork.model

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {
    private val compositeDisposable = CompositeDisposable()

    override fun searchMovie(
        query: String,
        success: (MovieSearchResponse) -> Unit,
        fail: (Throwable) -> Unit,
    ) {
        remoteDataSource.searchMovie(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                success(it)
            }, {
                fail(it)
            })
            .addTo(compositeDisposable)
    }

    override fun disposableClear() {
        this.compositeDisposable.clear()
    }
}