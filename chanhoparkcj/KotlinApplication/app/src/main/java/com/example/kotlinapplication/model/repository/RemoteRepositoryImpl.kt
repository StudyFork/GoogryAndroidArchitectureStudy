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
    private var service: RetrofitService= RetrofitClient.client
    var movieList: MutableLiveData<List<MovieItems>>
    var imageList: MutableLiveData<List<ImageItems>>
    var blogList: MutableLiveData<List<BlogItems>>
    var kinList: MutableLiveData<List<KinItems>>

    init {
        movieList = MutableLiveData()
        imageList = MutableLiveData()
        blogList = MutableLiveData()
        kinList = MutableLiveData()
    }
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