package com.example.myapplication.data.source

import com.example.myapplication.data.MovieDao
import com.example.myapplication.data.model.Movie

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