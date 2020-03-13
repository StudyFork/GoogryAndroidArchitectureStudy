package com.example.kangraemin.model.remote.datadao

import com.example.kangraemin.model.remote.datamodel.Movies
import com.example.kangraemin.util.RetrofitClient
import io.reactivex.Flowable

class MovieImpl : MovieInterface {

    override fun getSearchItems(
        clientId: String?,
        clientSecret: String?,
        display: String,
        start: String,
        query: String
    ): Flowable<Movies> {
        val service = RetrofitClient()
            .getClient("https://openapi.naver.com")
            .create(MovieInterface::class.java)
        return service.getSearchItems(
            display = display,
            start = start,
            query = query
        )
    }

    companion object {
        private var movieImpl: MovieImpl? = null

        fun getInstance(): MovieImpl {
            if (movieImpl == null) {
                synchronized(MovieImpl::class) {
                    if (movieImpl == null) {
                        movieImpl = MovieImpl()
                    }
                }
            }
            return movieImpl!!
        }
    }
}