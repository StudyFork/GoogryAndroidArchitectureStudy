package com.project.architecturestudy.data.source.local

import com.project.architecturestudy.data.source.local.room.MovieItemDao
import com.project.architecturestudy.data.source.local.room.MovieLocalItem
import io.reactivex.Observable

interface NaverMovieLocalDataSource {

    fun getMovieList(
        onSuccess: (Observable<List<MovieLocalItem>>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    )


    fun deleteMovieList(onGetDao: (MovieItemDao) -> Unit)
    fun saveMovieList(data: MovieLocalItem, onInsert: (Observable<Unit>) -> Unit)
}