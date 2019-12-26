package com.example.kotlinapplication.model.repository

class DataRepositoryImpl : DataRepository {
    private val remote: RemoteDataSource = RemoteDataSource()

    override fun callImageResources(query: String) = remote.getImageCall(query)

    override fun callBlogResources(query: String) = remote.getBlogCall(query)

    override fun callKinResources(query: String) = remote.getKinCall(query)

    override fun callMovieResources(query: String) = remote.getMovieCall(query)

}