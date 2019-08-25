package com.example.seonoh.seonohapp

import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.model.Market
import com.example.seonoh.seonohapp.network.Api
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CoinDataSourceImpl : CoinDataSource{

    private val BASE_URL = "https://api.upbit.com/"
    private fun createClient() = OkHttpClient.Builder()
        .addInterceptor(
            LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request("OH_req")
                .response("OH_res")
                .build()
        )
        .addNetworkInterceptor(StethoInterceptor())
        .build()


    val coinApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(createClient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)

    override fun getMarket(): Single<ArrayList<Market>> = coinApi.getMarketAll()

    override fun getCurrentPriceInfo(marketNameList: String):Single< ArrayList<CurrentPriceInfoModel>>
                = coinApi.getCurrentPriceInfo(marketNameList)
}