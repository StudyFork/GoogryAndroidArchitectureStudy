package io.github.sooakim.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.github.sooakim.BuildConfig
import io.github.sooakim.network.interceptor.SANaverAuthInterceptor
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SANetworkService {
    private const val GSON_DATE_FORMAT = "E, dd MMM yyyy HH:mm:ss Z"
    private val mNaverAuthInterceptor: SANaverAuthInterceptor by lazy {
        SANaverAuthInterceptor()
    }
    private val mOkHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(mNaverAuthInterceptor)
            .build()
    }
    private val mGson: Gson by lazy {
        GsonBuilder()
            .setDateFormat(GSON_DATE_FORMAT)
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }
    private val mRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.NAVER_BASE_URL)
            .client(mOkHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create(mGson))
            .build()
    }
}