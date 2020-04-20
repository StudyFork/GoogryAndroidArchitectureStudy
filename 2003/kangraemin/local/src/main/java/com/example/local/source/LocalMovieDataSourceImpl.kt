package com.example.local.source

import com.example.local.model.Movie
import io.reactivex.Completable
import io.reactivex.Single

internal class LocalMovieDataSourceImpl(
    private val movieDao: MovieDao
) : LocalMovieDataSource {
    override fun getAll(): Single<List<Movie>> {
        return movieDao
            .getAll()
    }

    override fun insertMovies(movies: List<Movie>): Completable {
        return movieDao
            .insertMovies(movies = movies)
    }

    override fun deleteAll(): Completable {
        return movieDao
            .deleteAll()
    }
}