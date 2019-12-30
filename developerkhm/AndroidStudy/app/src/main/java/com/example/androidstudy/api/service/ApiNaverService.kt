package com.example.androidstudy.api.service

import com.example.androidstudy.model.data.TotalModel
import com.example.androidstudy.common.CLIENT_ID
import com.example.androidstudy.common.CLIENT_KEY
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiNaverService {
    @Headers("X-Naver-Client-Id: $CLIENT_ID", "X-Naver-Client-Secret: $CLIENT_KEY")
    @GET("v1/search/{type}.json")
    fun requestSearchForNaver(@Path("type") type: String, @Query("query") query: String = "Test"): Single<TotalModel>
}