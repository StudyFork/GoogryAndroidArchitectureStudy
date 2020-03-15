package com.example.kangraemin.model.remote.datadao

import com.example.kangraemin.model.remote.datamodel.Movies
import io.reactivex.Single

interface MovieRemoteDataSource {
    fun getMovies(query: String): Single<Movies>
}