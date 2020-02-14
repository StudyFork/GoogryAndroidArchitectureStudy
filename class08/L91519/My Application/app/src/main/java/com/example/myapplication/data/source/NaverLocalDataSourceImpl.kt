package com.example.myapplication.data.source

import com.example.myapplication.Movie
import com.example.myapplication.MovieDao

class NaverLocalDataSourceImpl constructor(private val movieDao: MovieDao) : NaverLocalDataSource {

    override fun getRecentData(): Movie {
        return movieDao.getRecent()
    }

    override fun saveCache(movies: Movie) {
        movieDao.saveMovies(movies)
    }

    override fun delMovie() {
        movieDao.deleteAllResult()
    }

}