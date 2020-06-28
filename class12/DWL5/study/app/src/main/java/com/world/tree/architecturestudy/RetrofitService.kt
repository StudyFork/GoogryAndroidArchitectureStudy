package com.world.tree.architecturestudy

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val CLIENT_ID = "oJ6_BI6HBryWI4Fl2iyW"
const val CLIENT_SECRET = "TTXAMv3AtQ"
const val BASE_URL = "https://openapi.naver.com/"
interface RetrofitService {

    @Headers("X-Naver-Client-Id: {$CLIENT_ID}",
        "X-Naver-Client-Secret: {$CLIENT_SECRET}")
    @GET("v1/search/movie.json")
    fun requestSearchMovie(
        @Query("query") query:String,
        @Query("display") display:Int = 10,
        @Query("start") start: Int = 1
    ) : Call<Movie>

}

object SearchRetrofit {
    private val retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    fun getService(): RetrofitService = retrofit.create(RetrofitService::class.java)

}