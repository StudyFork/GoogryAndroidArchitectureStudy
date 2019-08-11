package com.example.seonoh.seonohapp.network

import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/ticker")
    fun getCurrentPriceInfo(
        @Query("markets") page: Int): Single<CurrentPriceInfoModel>

}