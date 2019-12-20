package com.example.kotlinapplication.model.repository

import com.example.kotlinapplication.network.RetrofitClient
import com.example.kotlinapplication.network.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class RemoteDataSourceImpl(listener:DataRepository.response) {
    private var service: RetrofitService= RetrofitClient.client
    private var response :DataRepository.response = listener

    fun getMovieCall(query: String) {
        service.getMovieCall(query)
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread())
            .subscribe(Consumer {
                if (it.items.isNotEmpty()) {
                    response.responseMovieResources(it.items)
                }
            })
    }

    fun getImageCall(query: String) {
        service.getImageCall(query)
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread())
            .subscribe(Consumer {
                if (it.items.isNotEmpty()) {
                    response.responseImageResources(it.items)
                }
            })
    }

    fun getBlogCall(query: String) {
        service.getBlogCall(query)
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread())
            .subscribe(Consumer {
                if (it.items.isNotEmpty()) {
                    response.responseBlogResources(it.items)
                }
            })
    }

    fun getKinCall(query: String) {
        service.getKinCall(query)
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread())
            .subscribe(Consumer {
                if (!it.items.isNotEmpty()) {
                    response.responseKinResources(it.items)
                }
            })
    }

}