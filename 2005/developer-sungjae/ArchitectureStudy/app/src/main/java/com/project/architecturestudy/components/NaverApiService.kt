package com.project.architecturestudy.components

import com.project.architecturestudy.data.model.NaverApiData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverApiService {

    @GET("v1/search/movie.json")
    fun getMovies(@Query("query") title: String): Single<NaverApiData>

}