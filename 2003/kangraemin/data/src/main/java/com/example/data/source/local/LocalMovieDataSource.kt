package com.example.data.source.local

import com.example.data.model.Movie
import io.reactivex.Completable
import io.reactivex.Single

interface LocalMovieDataSource {
    fun getAll(): Single<List<Movie>>
    fun insertMovies(movies: List<Movie>): Completable
    fun deleteAll(): Completable
}
