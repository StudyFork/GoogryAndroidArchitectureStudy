package com.ironelder.androidarchitecture.utils

import com.ironelder.androidarchitecture.common.CLIENT_ID
import com.ironelder.androidarchitecture.common.CLIENT_KEY
import com.ironelder.androidarchitecture.data.TotalModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

object RetrofitForNaver {
    private var mRetrofit = Retrofit.Builder()
        .baseUrl("https://openapi.naver.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getSearchForNaver(): SearchForNaver = mRetrofit.create(SearchForNaver::class.java)

//    fun getSearchService(): SearchForKakao = mRetrofit.create(SearchForKakao::class.java)

    interface SearchForNaver {
        @Headers("X-Naver-Client-Id: $CLIENT_ID", "X-Naver-Client-Secret: $CLIENT_KEY")
        @GET("v1/search/{type}.json")
        fun requestSearchForNaver(@Path("type") type: String, @Query("query") query: String = "Test"): Call<TotalModel>
    }
}