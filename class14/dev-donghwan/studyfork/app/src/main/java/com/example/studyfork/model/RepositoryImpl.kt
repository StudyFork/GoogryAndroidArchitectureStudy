package com.example.studyfork.model

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo

class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {

    override fun searchMovie(
        query: String,
        success: (MovieSearchResponse) -> Unit,
        fail: (Throwable) -> Unit,
    ): Disposable {
        return remoteDataSource.searchMovie(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                success(it)
            }, {
                fail(it)
            })
    }
}