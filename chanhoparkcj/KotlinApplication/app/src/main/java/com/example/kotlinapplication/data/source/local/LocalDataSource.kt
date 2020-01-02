package com.example.kotlinapplication.data.source.local

import com.example.kotlinapplication.data.model.*
import io.reactivex.Single

interface LocalDataSource {
    fun setMovieCall(list: List<MovieItem>)
    fun setImageCall(list: List<ImageItem>)
    fun setBlogCall(list: List<BlogItem>)
    fun setKinCall(list: List<KinItem>)

    fun getMovieCall(): List<MovieItem>
    fun getImageCall(): List<ImageItem>
    fun getBlogCall(): List<BlogItem>
    fun getKinCall(): List<KinItem>
}