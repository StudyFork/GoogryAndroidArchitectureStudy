package app.ch.study.data.repository

import app.ch.study.data.remote.response.MovieResponse
import io.reactivex.Observable

interface NaverQueryRepository {

    fun searchMovie(query: String): Observable<MovieResponse>

    fun getQuery(): String

}