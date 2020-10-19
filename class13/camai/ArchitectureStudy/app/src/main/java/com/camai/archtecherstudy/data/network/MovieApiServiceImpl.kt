package com.camai.archtecherstudy.data.network

import com.camai.archtecherstudy.BuildConfig
import com.camai.archtecherstudy.utils.Constans.Companion.BASE_URL_NAVER_API
import com.camai.archtecherstudy.utils.Constans.Companion.CLIENT_ID
import com.camai.archtecherstudy.utils.Constans.Companion.CLIENT_SECRET
import com.camai.archtecherstudy.utils.Constans.Companion.CONNECT_TIMEOUT
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MovieApiServiceImpl @Inject constructor() {

    private var httpLoggingInterceptor = HttpLoggingInterceptor()
    private var okclient: OkHttpClient.Builder
    private var create: Retrofit


    init {
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = BODY
        } else {
            httpLoggingInterceptor.level = NONE
        }

        val headerInterceptor = Interceptor {
            val request = it.request()
                .newBuilder()
                .addHeader("X-Naver-Client-Id", CLIENT_ID)
                .addHeader("X-Naver-Client-Secret", CLIENT_SECRET)
                .build()
            return@Interceptor it.proceed(request)
        }

        okclient = OkHttpClient.Builder().apply {
            addInterceptor(headerInterceptor)
            addInterceptor(httpLoggingInterceptor)
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            build()
        }



        create = Retrofit.Builder()
            .baseUrl(BASE_URL_NAVER_API)
            .client(okclient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service: MovieApiService by lazy {
        create.create(MovieApiService::class.java)
    }


}