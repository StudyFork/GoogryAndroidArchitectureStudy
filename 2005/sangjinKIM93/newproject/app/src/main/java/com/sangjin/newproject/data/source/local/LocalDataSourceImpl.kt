package com.sangjin.newproject.data.source.local

import com.sangjin.newproject.data.model.Movie

class LocalDataSourceImpl : LocalDataSource {

    override fun getMovieData(
        query: String,
        roomDB: RoomDB,
        onSuccess: (movies: List<Movie>) -> Unit
    ) {
        val movies = roomDB.movieDao.selectMovies(query)

        onSuccess(movies)
    }
}