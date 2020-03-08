package io.github.sooakim.data.remote.api

import io.github.sooakim.data.remote.model.SAMovieModel
import io.github.sooakim.data.remote.model.response.SANaverSearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SANaverMovieApi {
    @GET("v1/search/movie.json")
    fun getSearchMovie(@Query("query") query: String): Single<SANaverSearchResponse<SAMovieModel>>
}