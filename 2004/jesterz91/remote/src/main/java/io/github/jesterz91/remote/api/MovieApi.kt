package io.github.jesterz91.remote.api

import io.github.jesterz91.remote.model.MovieSearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("v1/search/movie.json")
    fun searchMovie(@Query("query") query: String): Single<MovieSearchResponse>
}