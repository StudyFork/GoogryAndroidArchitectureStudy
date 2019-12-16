package com.example.kotlinapplication.model.repository

class DataRepositoryImpl : DataRepository {
    var remote: RemoteRepositoryImpl

    init {
        remote = RemoteRepositoryImpl()
    }

    override fun getMovieResources(query: String) {
        remote.getMovieCall(query)
    }

    override fun getImageResources(query: String) {
        remote.getImageCall(query)
    }

    override fun getBlogResources(query: String) {
        remote.getBlogCall(query)
    }

    override fun getKinResources(query: String) {
        remote.getKinCall(query)
    }
}