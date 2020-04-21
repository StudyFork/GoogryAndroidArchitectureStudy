package io.github.sooakim.remote.api

import io.github.sooakim.remote.model.SAMovieModel
import io.github.sooakim.remote.model.response.SANaverSearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

internal interface SANaverMovieApi {
    @GET("v1/search/movie.json")
    fun getSearchMovie(@Query("query") query: String): Single<SANaverSearchResponse<SAMovieModel>>
}