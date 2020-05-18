package com.project.architecturestudy.data.source.local

import android.content.Context
import com.project.architecturestudy.data.model.Movie
import com.project.architecturestudy.data.source.local.room.MovieLocal
import com.project.architecturestudy.data.source.local.room.MovieRoomDataBase

interface NaverMovieLocalDataSource {

    var roomDataBase: MovieRoomDataBase?
    fun getMovieList(context: Context, Success: (ArrayList<MovieLocal>) -> Unit, Failure: (Throwable) -> Unit)
    fun saveMovieList(context: Context, data: ArrayList<Movie.Items>)
}