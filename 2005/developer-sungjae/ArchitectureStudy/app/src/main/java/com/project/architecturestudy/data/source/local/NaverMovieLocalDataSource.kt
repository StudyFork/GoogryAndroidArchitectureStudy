package com.project.architecturestudy.data.source.local

import com.project.architecturestudy.data.model.MovieItem
import com.project.architecturestudy.data.model.NaverApiData
import com.project.architecturestudy.data.source.local.room.MovieRoomDataBase
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

interface NaverMovieLocalDataSource {

    val disposable: CompositeDisposable
    var roomDataBase: MovieRoomDataBase?
    fun getMovieList(Success: (ArrayList<MovieItem>) -> Unit, Failure: (t: Throwable) -> Unit)
    fun saveMovieList(data: Single<NaverApiData>)
    fun dispose()
}