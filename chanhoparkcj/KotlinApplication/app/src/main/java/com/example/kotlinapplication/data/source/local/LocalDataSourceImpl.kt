package com.example.kotlinapplication.data.source.local

import com.example.kotlinapplication.data.model.BlogItem
import com.example.kotlinapplication.data.model.ImageItem
import com.example.kotlinapplication.data.model.KinItem
import com.example.kotlinapplication.data.model.MovieItem
import com.orhanobut.hawk.Hawk

class LocalDataSourceImpl : LocalDataSource {
    override fun getMovieCall(): List<MovieItem> {
        if (Hawk.get("movieList", null) != null) {
            return Hawk.get("movieList")
        } else {
            return emptyList()
        }
    }

    override fun getImageCall(): List<ImageItem> {
        if (Hawk.get("imageList", null) != null) {
            return Hawk.get("imageList")
        } else {
            return emptyList()
        }
    }

    override fun getBlogCall(): List<BlogItem> {
        if (Hawk.get("blogList", null) != null) {
            return Hawk.get("blogList")
        } else {
            return emptyList()
        }
    }

    override fun getKinCall(): List<KinItem> {
        if (Hawk.get("kinList", null) != null) {
            return Hawk.get("kinList")
        } else {
            return emptyList()
        }
    }

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

}