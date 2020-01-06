package com.example.kotlinapplication.data.source.local

import com.example.kotlinapplication.data.model.BlogItem
import com.example.kotlinapplication.data.model.ImageItem
import com.example.kotlinapplication.data.model.KinItem
import com.example.kotlinapplication.data.model.MovieItem
import com.orhanobut.hawk.Hawk

class LocalDataSourceImpl : LocalDataSource {
    override fun setMovieCall(list: List<MovieItem>) {
        Hawk.put("movieList", list)
    }

    override fun setImageCall(list: List<ImageItem>) {
        Hawk.put("imageList", list)
    }

    override fun setBlogCall(list: List<BlogItem>) {
        Hawk.put("blogList", list)
    }

    override fun setKinCall(list: List<KinItem>) {
        Hawk.put("kinList", list)
    }

    override fun getMovieCall(): List<MovieItem> = Hawk.get("movieList", emptyList())

    override fun getImageCall(): List<ImageItem> = Hawk.get("imageList", emptyList())

    override fun getBlogCall(): List<BlogItem> = Hawk.get("blogList", emptyList())

    override fun getKinCall(): List<KinItem> = Hawk.get("kinList", emptyList())

}