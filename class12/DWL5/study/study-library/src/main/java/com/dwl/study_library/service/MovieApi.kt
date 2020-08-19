package com.dwl.study_library.service

import com.dwl.study_library.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

const val CLIENT_ID = "oJ6_BI6HBryWI4Fl2iyW"
const val CLIENT_SECRET = "TTXAMv3AtQ"
const val BASE_URL = "https://openapi.naver.com/"
interface MovieApi {
    @GET("v1/search/movie.json")
    fun requestSearchMovie(
        @Header("X-Naver-Client-Id") id : String = CLIENT_ID,
        @Header("X-Naver-Client-Secret") pw : String = CLIENT_SECRET,
        @Query("query") query:String,
        @Query("display") display:Int = 10,
        @Query("start") start: Int = 1
    ) : retrofit2.Call<Movie>

}
