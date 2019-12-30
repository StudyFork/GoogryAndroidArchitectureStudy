package com.example.kotlinapplication.data.source.remote

import com.example.kotlinapplication.data.model.*
import com.example.kotlinapplication.network.RetrofitClient
import com.example.kotlinapplication.network.RetrofitService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers

class RemoteDataSourceImpl : RemoteDataSource {
    private val service: RetrofitService = RetrofitClient.client

    override fun getMovieCall(query: String): Single<ResponseItems<MovieItem>> {
        return service.getMovieCall(query)
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread())
    }

    override fun getImageCall(query: String): Single<ResponseItems<ImageItem>> {
        return service.getImageCall(query)
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread())
    }

    override fun getBlogCall(query: String): Single<ResponseItems<BlogItem>> {
        return service.getBlogCall(query)
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread())
    }

    override fun getKinCall(query: String): Single<ResponseItems<KinItem>> {
        return service.getKinCall(query)
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread())
    }
}