package com.example.kangraemin.model.local.datadao

import com.example.kangraemin.model.AppDatabase
import com.example.kangraemin.model.local.datamodel.Movie
import io.reactivex.Completable
import io.reactivex.Single

class LocalMovieImpl(
    private val db: AppDatabase
) : LocalMovieDataSource {
    override fun getAll(): Single<List<Movie>> {
        return db
            .movieDao()
            .getAll()
    }

    override fun insertMovies(movies: List<Movie>): Completable {
        return db
            .movieDao()
            .insertMovies(movies = movies)
    }

    override fun deleteAll(): Completable {
        return db
            .movieDao()
            .deleteAll()
    }
}