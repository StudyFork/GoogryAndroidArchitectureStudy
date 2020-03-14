package com.example.kangraemin.model.local.datadao

import com.example.kangraemin.model.local.datamodel.Movie
import io.reactivex.Completable
import io.reactivex.Single

class LocalMovieImpl(
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