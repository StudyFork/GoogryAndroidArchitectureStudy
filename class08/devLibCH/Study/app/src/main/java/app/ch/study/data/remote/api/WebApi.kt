package app.ch.study.data.remote.api

import app.ch.study.data.remote.response.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WebApi {

    @GET("/v1/search/movie.json")
    fun searchMovie(@Query("query") query: String?): Single<MovieResponse>

}