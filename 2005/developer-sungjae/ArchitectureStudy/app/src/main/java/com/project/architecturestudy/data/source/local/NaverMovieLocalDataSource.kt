package com.project.architecturestudy.data.source.local

import com.project.architecturestudy.data.source.local.room.MovieLocalItem
import io.reactivex.Observable

interface NaverMovieLocalDataSource {

    fun getMovieList(onSuccess: (Observable<List<MovieLocalItem>>) -> Unit, onFailure: (t: Throwable) -> Unit)
    fun saveMovieList(data: MovieLocalItem)
}