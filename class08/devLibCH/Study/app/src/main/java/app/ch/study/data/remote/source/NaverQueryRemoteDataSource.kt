package app.ch.study.data.remote.source

import app.ch.study.data.remote.response.MovieResponse
import io.reactivex.Single

interface NaverQueryRemoteDataSource {

    fun searchMovie(query: String) : Single<MovieResponse>

}