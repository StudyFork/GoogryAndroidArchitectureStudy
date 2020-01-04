package com.example.kotlinapplication.data.source.local

import com.example.kotlinapplication.data.model.*
import com.orhanobut.hawk.Hawk
import io.reactivex.Single

class LocalDataSourceImpl : LocalDataSource {
    override fun setMovieCall(list: Single<ResponseItems<MovieItem>>) {
        list.subscribe(
            { datas -> Hawk.put("movieList",datas.items)}
        )
    }

    override fun setImageCall(list: Single<ResponseItems<ImageItem>>) {
        list.subscribe(
            { datas -> Hawk.put("imageList",datas.items)}
        )
    }

    override fun setBlogCall(list: Single<ResponseItems<BlogItem>>) {
        list.subscribe(
            { datas -> Hawk.put("blogList",datas.items)}
        )
    }

    override fun setKinCall(list: Single<ResponseItems<KinItem>>) {
        list.subscribe(
            { datas -> Hawk.put("kinList",datas.items)}
        )
    }

    override fun getMovieCall(): List<MovieItem> = Hawk.get("movieList", emptyList())

    override fun getImageCall(): List<ImageItem> =Hawk.get("imageList", emptyList())

    override fun getBlogCall(): List<BlogItem> =Hawk.get("blogList", emptyList())

    override fun getKinCall(): List<KinItem> =Hawk.get("kinList", emptyList())

}