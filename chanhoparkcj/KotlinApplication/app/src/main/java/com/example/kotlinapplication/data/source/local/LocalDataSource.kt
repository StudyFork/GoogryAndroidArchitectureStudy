package com.example.kotlinapplication.data.source.local

import com.example.kotlinapplication.data.model.*
import io.reactivex.Single

interface LocalDataSource {
    fun setMovieCall(list: Single<ResponseItems<MovieItem>>)
    fun setImageCall(list: Single<ResponseItems<ImageItem>>)
    fun setBlogCall(list: Single<ResponseItems<BlogItem>>)
    fun setKinCall(list: Single<ResponseItems<KinItem>>)

    fun getMovieCall(): List<MovieItem>?
    fun getImageCall(): List<ImageItem>?
    fun getBlogCall(): List<BlogItem>?
    fun getKinCall(): List<KinItem>?
}