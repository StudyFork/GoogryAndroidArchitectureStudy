package com.example.studyfork.data.repository

import com.example.studyfork.data.model.MovieSearchResponse
import com.example.studyfork.data.remote.RemoteDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

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