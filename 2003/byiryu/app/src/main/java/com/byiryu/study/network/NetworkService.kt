package com.byiryu.study.network

import com.byiryu.study.api.Apis
import com.byiryu.study.conf.Config.BASE_URL
import com.byiryu.study.conf.Config.NAVER_CLIENT_ID
import com.byiryu.study.conf.Config.NAVER_CLIENT_SECRET
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkService {

    val headerInterceptor = HeaderInterceptor()
    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    val client = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .readTimeout(20000, TimeUnit.MILLISECONDS)
        .connectTimeout(10000, TimeUnit.MILLISECONDS)
        .retryOnConnectionFailure(true)
        .build()


    val gson = GsonBuilder().serializeSpecialFloatingPointValues()
        .serializeNulls()
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .client(client)
        .build()
        .create(Apis::class.java)
}

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("X-Naver-Client-Id", NAVER_CLIENT_ID)
            .addHeader("X-Naver-Client-Secret", NAVER_CLIENT_SECRET)
            .build()
        return chain.proceed(request)
    }

}
