package com.example.kotlinapplication.model.repository

class DataRepositoryImpl(listener:DataRepository.response) : DataRepository.call {
    private val remote: RemoteDataSourceImpl=RemoteDataSourceImpl(listener)

    override fun callMovieResources(query: String) {
        remote.getMovieCall(query)
    }

    override fun callImageResources(query: String) {
        remote.getImageCall(query)
    }

    override fun callBlogResources(query: String) {
        remote.getBlogCall(query)
    }

    override fun callKinResources(query: String) {
        remote.getKinCall(query)
    }





}