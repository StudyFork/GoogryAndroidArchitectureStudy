package com.ironelder.androidarchitecture.data

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchForNaver {

    //TODO : Rx의 적용으로 더이상 사용하지 않게될 함수 Rx적용 완료되면 삭제 요망.
    @GET("v1/search/{type}.json")
    fun requestSearchForNaver(@Path("type") type: String, @Query("query") query: String = "Test"): Call<TotalModel>

    @GET("v1/search/{type}.json")
    fun requestSearchForNaverWithAdapter(@Path("type") type: String, @Query("query") query: String = "Test"): Single<TotalModel>
}
