package io.github.sooakim.network.api

import io.github.sooakim.network.model.SAMovieModel
import io.github.sooakim.network.model.response.SANaverSearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SANaverMovieApi : SAApi {
    @GET("v1/search/movie.json")
    fun getSearchMovie(@Query("query") query: String): Single<SANaverSearchResponse<SAMovieModel>>
}