package com.example.kotlinapplication.data.repository

import com.example.kotlinapplication.data.model.*
import com.example.kotlinapplication.data.source.local.LocalDataSourceImpl
import com.example.kotlinapplication.data.source.remote.RemoteDataSourceImpl
import io.reactivex.Single

class DataRepositoryImpl : DataRepository {
    private val remote: RemoteDataSourceImpl = RemoteDataSourceImpl()
    private val local: LocalDataSourceImpl = LocalDataSourceImpl()
    private lateinit var imageDatas: Single<ResponseItems<ImageItem>>
    private lateinit var blogDatas: Single<ResponseItems<BlogItem>>
    private lateinit var kinDatas: Single<ResponseItems<KinItem>>
    private lateinit var movieDatas: Single<ResponseItems<MovieItem>>

    override fun callImageResources(query: String): Single<ResponseItems<ImageItem>> {
        imageDatas = remote.getImageCall(query)
        local.setImageCall(imageDatas)
        return imageDatas
    }

    override fun callBlogResources(query: String): Single<ResponseItems<BlogItem>> {
        blogDatas = remote.getBlogCall(query)
        local.setBlogCall(blogDatas)
        return blogDatas
    }

    override fun callKinResources(query: String): Single<ResponseItems<KinItem>> {
        kinDatas = remote.getKinCall(query)
        local.setKinCall(kinDatas)
        return kinDatas
    }

    override fun callMovieResources(query: String): Single<ResponseItems<MovieItem>> {
        movieDatas = remote.getMovieCall(query)
        local.setMovieCall(movieDatas)
        return movieDatas
    }

}