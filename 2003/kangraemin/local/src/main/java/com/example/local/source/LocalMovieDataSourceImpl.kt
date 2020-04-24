package com.example.local.source

import com.example.data.model.Movie
import com.example.data.source.local.LocalMovieDataSource
import com.example.local.mapper.LocalMovieMapper
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

internal class LocalMovieDataSourceImpl(
    private val movieDao: MovieDao
) : LocalMovieDataSource {
    override fun getAll(): Single<List<Movie>> {
        return movieDao
            .getAll()
            .subscribeOn(Schedulers.io())
            .map {
                LocalMovieMapper.localMovieToDataMovie(it)
            }
    }

    override fun insertMovies(movies: List<Movie>): Completable {
        return movieDao
            .insertMovies(movies = LocalMovieMapper.remoteMovieToLocalMovie(movies))
            .subscribeOn(Schedulers.io())
    }

    override fun deleteAll(): Completable {
        return movieDao
            .deleteAll()
            .subscribeOn(Schedulers.io())
    }
}