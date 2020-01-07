package com.example.kotlinapplication.data.source.remote

import com.example.kotlinapplication.data.model.*
import io.reactivex.Single

interface RemoteDataSource {
    fun getMovieCall(query: String): Single<ResponseItems<MovieItem>>
    fun getImageCall(query: String): Single<ResponseItems<ImageItem>>
    fun getBlogCall(query: String): Single<ResponseItems<BlogItem>>
    fun getKinCall(query: String): Single<ResponseItems<KinItem>>
}