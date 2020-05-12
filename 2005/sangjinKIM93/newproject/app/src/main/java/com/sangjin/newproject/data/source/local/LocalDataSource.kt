package com.sangjin.newproject.data.source.local

import com.sangjin.newproject.data.model.Movie

interface LocalDataSource {

    fun getMovieData(
        query: String,
        roomDB: RoomDB,
        onSuccess: ((movies: List<Movie>) -> Unit)
    )

    fun saveMovieData(
        movies: List<Movie>,
        roomDB: RoomDB
    )

}