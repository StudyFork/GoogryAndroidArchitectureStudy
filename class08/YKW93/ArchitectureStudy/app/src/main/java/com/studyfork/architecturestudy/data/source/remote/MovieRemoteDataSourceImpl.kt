package com.studyfork.architecturestudy.data.source.remote

import com.studyfork.architecturestudy.data.model.MovieResponse
import com.studyfork.architecturestudy.network.ApiClient
import com.studyfork.architecturestudy.extension.applySchedulers
import io.reactivex.disposables.Disposable

class MovieRemoteDataSourceImpl : MovieRemoteDataSource {
    override fun getMovieList(
        query: String,
        loading: (Boolean) -> Unit,
        success: (MovieResponse) -> Unit,
        error: (Throwable) -> Unit
    ): Disposable {
        return ApiClient.apiService.getMovieList(query)
            .applySchedulers()
            .doOnSubscribe {
                loading(true)
            }
            .doAfterTerminate {
                loading(false)
            }
            .subscribe({
                success(it)
            }, {
                error(it)
            })

    }

}