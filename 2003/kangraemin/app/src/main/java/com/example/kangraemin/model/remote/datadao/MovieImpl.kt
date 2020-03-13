package com.example.kangraemin.model.remote.datadao

import com.example.kangraemin.model.remote.datamodel.Movies
import com.example.kangraemin.util.RetrofitClient
import io.reactivex.Flowable

class MovieImpl(
    private val movieApi: MovieInterface
) : MovieDataSource {
    override fun getMovies(query: String): Flowable<Movies> {
        return movieApi.getSearchItems(query = query)
    }

    companion object {
        private var movieImpl: MovieImpl? = null

        fun getInstance(): MovieImpl {
            if (movieImpl == null) {
                synchronized(MovieImpl::class) {
                    if (movieImpl == null) {
                        movieImpl = MovieImpl(RetrofitClient.getMovieApi())
                    }
                }
            }
            return movieImpl!!
        }
    }
}