package com.example.kotlinapplication.model.repository

import androidx.lifecycle.MutableLiveData
import com.example.kotlinapplication.model.BlogItems
import com.example.kotlinapplication.model.ImageItems
import com.example.kotlinapplication.model.KinItems
import com.example.kotlinapplication.model.MovieItems
import com.example.kotlinapplication.network.RetrofitClient
import com.example.kotlinapplication.network.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class RemoteRepositoryImpl {
    private var service: RetrofitService = RetrofitClient.client.create(RetrofitService::class.java)
    val movieList: MutableLiveData<List<MovieItems>> = MutableLiveData()
    val imageList: MutableLiveData<List<ImageItems>> = MutableLiveData()
    val blogList: MutableLiveData<List<BlogItems>> = MutableLiveData()
    val kinList:MutableLiveData<List<KinItems>> = MutableLiveData()

    fun getMovieCall(query: String) {
        service.getMovieCall(query)
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread())
            .subscribe(Consumer {
                if (it.items.isNotEmpty()) {
                    movieList.value = it.items
                }
            })
    }

    fun getImageCall(query: String) {
        service.getImageCall(query)
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread())
            .subscribe(Consumer {
                if (it.items.isNotEmpty()) {
                    imageList.value = it.items
                }
            })
    }

    fun getBlogCall(query: String) {
        service.getBlogCall(query)
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread())
            .subscribe(Consumer {
                if (it.items.isNotEmpty()) {
                    blogList.value = it.items
                }
            })
    }

    fun getKinCall(query: String) {
        service.getKinCall(query)
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread())
            .subscribe(Consumer {
                if (!it.items.isNotEmpty()) {
                    kinList.value = it.items
                }
            })
    }

}