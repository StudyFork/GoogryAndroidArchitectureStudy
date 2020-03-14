package com.example.kangraemin.model.remote.datadao

import com.example.kangraemin.model.remote.datamodel.Movies
import io.reactivex.Flowable

class MovieImpl(
    private val movieApi: MovieInterface
) : RemoteMovieDataSource {
    override fun getMovies(query: String): Flowable<Movies> {
        return movieApi.getSearchItems(query = query)
    }
}