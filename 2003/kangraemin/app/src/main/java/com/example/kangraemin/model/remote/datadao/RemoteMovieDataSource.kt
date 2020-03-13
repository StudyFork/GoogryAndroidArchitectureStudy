package com.example.kangraemin.model.remote.datadao

import com.example.kangraemin.model.remote.datamodel.Movies
import io.reactivex.Flowable

interface RemoteMovieDataSource {
    fun getMovies(query: String): Flowable<Movies>
}