package com.example.kotlinapplication.model.repository

import com.example.kotlinapplication.model.*
import io.reactivex.Single

class DataRepositoryImpl : DataRepository {
    private val remote: RemoteDataSourceImpl = RemoteDataSourceImpl()

    override fun callImageResources(query: String): Single<ResponseItems<ImageItem>> {
        return remote.getImageCall(query)
    }

    override fun callBlogResources(query: String): Single<ResponseItems<BlogItem>> {
        return remote.getBlogCall(query)
    }

    override fun callKinResources(query: String): Single<ResponseItems<KinItem>> {
        return remote.getKinCall(query)
    }

    override fun callMovieResources(query: String): Single<ResponseItems<MovieItem>> {
        return remote.getMovieCall(query)
    }
}