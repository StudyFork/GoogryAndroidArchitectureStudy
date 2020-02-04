package com.example.myapplication.data.source

import com.example.myapplication.Movie
import com.example.myapplication.MovieDatabase

object NaverLocalDataSourceImpl : NaverLocalDataSource {

    override fun getResentData(db: MovieDatabase) {
        db.movieDao().getResent()
    }

    override fun saveCache(db: MovieDatabase, movies: List<Movie>) {
        db.movieDao().saveMovies(*movies.toTypedArray())
    }

    override fun delMovie(db: MovieDatabase) {
        db.movieDao().deleteAllResult()
    }

}