package com.example.kotlinapplication.data.repository

import com.example.kotlinapplication.data.source.remote.RemoteDataSourceImpl

class DataRepositoryImpl : DataRepository {
    private val remote: RemoteDataSourceImpl =
        RemoteDataSourceImpl()

    override fun callImageResources(query: String) = remote.getImageCall(query)

    override fun callBlogResources(query: String) = remote.getBlogCall(query)

    override fun callKinResources(query: String) = remote.getKinCall(query)

    override fun callMovieResources(query: String) = remote.getMovieCall(query)

}