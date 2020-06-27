package com.project.architecturestudy.data.source.local

import com.project.architecturestudy.data.source.local.room.MovieItemDao
import com.project.architecturestudy.data.source.local.room.MovieLocalItem
import io.reactivex.Observable

interface NaverMovieLocalDataSource {

    fun getMovieList(): Observable<List<MovieLocalItem>>

    fun deleteMovieList(): MovieItemDao

    fun saveMovieList(data: MovieLocalItem): Observable<Unit>
}