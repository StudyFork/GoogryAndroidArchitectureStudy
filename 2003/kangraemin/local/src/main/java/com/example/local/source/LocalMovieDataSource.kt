package com.example.local.source

import com.example.local.model.Movie
import io.reactivex.Completable
import io.reactivex.Single

interface LocalMovieDataSource {
    fun getAll(): Single<List<Movie>>
    fun insertMovies(movies: List<Movie>): Completable
    fun deleteAll(): Completable
}
