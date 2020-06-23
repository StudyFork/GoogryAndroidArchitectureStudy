package com.project.architecturestudy.data.repository

import com.project.architecturestudy.data.model.NaverApiData
import com.project.architecturestudy.data.source.local.room.MovieItemDao
import com.project.architecturestudy.data.source.local.room.MovieLocalItem
import io.reactivex.Observable
import io.reactivex.Single

interface NaverMovieRepository {

    fun getMovieList(keyWord: String): Single<NaverApiData>

    fun getCashedMovieList(): Observable<List<MovieLocalItem>>

    fun saveMovieList(movieList: MovieLocalItem): Observable<Unit>

    fun deleteMovieList(): MovieItemDao
}