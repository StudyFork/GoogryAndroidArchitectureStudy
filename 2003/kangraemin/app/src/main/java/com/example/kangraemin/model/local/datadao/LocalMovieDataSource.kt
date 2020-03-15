package com.example.kangraemin.model.local.datadao

import com.example.kangraemin.model.local.datamodel.Movie
import io.reactivex.Completable
import io.reactivex.Single

interface LocalMovieDataSource {
    fun getAll(): Single<List<Movie>>
    fun insertMovies(movies: List<Movie>): Completable
    fun deleteAll(): Completable
}
