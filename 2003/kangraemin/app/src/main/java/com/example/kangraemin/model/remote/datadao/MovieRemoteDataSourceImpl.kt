package com.example.kangraemin.model.remote.datadao

import com.example.kangraemin.model.remote.datamodel.Movies
import io.reactivex.Flowable

class MovieRemoteDataSourceImpl(
    private val movieApi: MovieApi
) : MovieRemoteDataSource {
    override fun getMovies(query: String): Flowable<Movies> {
        return movieApi.getSearchItems(query = query)
    }
}