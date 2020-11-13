package kr.dktsudgg.androidarchitecturestudy.api.naver

import kr.dktsudgg.androidarchitecturestudy.data.model.NaverMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverMovieApi {
    @GET("v1/search/movie.json")
    fun searchMovies(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Query("query") query: String,
        @Query("display") display: Int? = null,
        @Query("start") start: Int? = null
    ): Call<NaverMovieResponse>
}