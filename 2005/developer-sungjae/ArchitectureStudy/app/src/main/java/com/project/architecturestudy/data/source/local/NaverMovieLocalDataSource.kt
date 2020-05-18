package com.project.architecturestudy.data.source.local

import com.project.architecturestudy.data.model.Movie
import com.project.architecturestudy.data.source.local.room.MovieLocal
import com.project.architecturestudy.data.source.local.room.MovieRoomDataBase
import io.reactivex.disposables.CompositeDisposable

interface NaverMovieLocalDataSource {

    var roomDataBase: MovieRoomDataBase?
    fun getMovieList(Success: (ArrayList<MovieLocal>) -> Unit, Failure: (Throwable) -> Unit)
    fun saveMovieList(data: ArrayList<Movie.Items>)
    val disposable: CompositeDisposable
}