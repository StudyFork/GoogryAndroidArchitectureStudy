package com.project.architecturestudy.data.source.remote

import com.project.architecturestudy.components.NaverApiService
import com.project.architecturestudy.data.model.NaverApiData
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

interface NaverMovieRemoteDataSource {

    val service: NaverApiService
    val disposable: CompositeDisposable
    fun getMovieList(
        keyWord: String,
        onGetRemoteData: (Single<NaverApiData>) -> Unit
    )
}