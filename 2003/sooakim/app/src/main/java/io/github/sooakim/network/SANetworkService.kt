package io.github.sooakim.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.github.sooakim.BuildConfig
import io.github.sooakim.network.api.SANaverMovieApi
import io.github.sooakim.network.interceptor.SANaverAuthInterceptor
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SANetworkService {
    private const val GSON_DATE_FORMAT = "E, dd MMM yyyy HH:mm:ss Z"
    private val naverAuthInterceptor: SANaverAuthInterceptor by lazy {
        SANaverAuthInterceptor()
    }
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(naverAuthInterceptor)
            .build()
    }
    private val gson: Gson by lazy {
        GsonBuilder()
            .setDateFormat(GSON_DATE_FORMAT)
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.NAVER_BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val movieApi: SANaverMovieApi by lazy {
        retrofit.create(SANaverMovieApi::class.java)
    }
}