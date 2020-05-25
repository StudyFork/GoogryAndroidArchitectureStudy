package com.sangjin.newproject

import com.sangjin.newproject.data.model.NaverMovieResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitService {

    @Headers(
        "X-Naver-Client-Id: ${BuildConfig.X_Naver_Client_Id}",
        "X-Naver-Client-Secret: ${BuildConfig.X_Naver_Client_Secret}"
    )
    @GET("/v1/search/movie.json")
    fun requestMovieList(
        @Query("query") keyword: String
    ): Single<NaverMovieResponse>

}

//retrofit 객체 생성
private val retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.NAVER_API_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()

//retrofitService 객체 생성
object MovieApi{
    val retrofitService : RetrofitService by lazy {
        retrofit.create(RetrofitService::class.java)
    }
}
