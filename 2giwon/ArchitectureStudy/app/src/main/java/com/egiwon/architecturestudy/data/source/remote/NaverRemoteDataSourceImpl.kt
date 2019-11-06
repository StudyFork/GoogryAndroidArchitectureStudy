package com.egiwon.architecturestudy.data.source.remote

import android.util.Log
import com.egiwon.architecturestudy.BuildConfig
import com.egiwon.architecturestudy.data.Content
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NaverRemoteDataSourceImpl :
    NaverRemoteDataSource {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://openapi.naver.com/v1/")
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG) {
                            HttpLoggingInterceptor.Level.BODY
                        } else {
                            HttpLoggingInterceptor.Level.NONE
                        }
                    }
                )
                .addInterceptor { chain ->
                    chain.proceed(
                        chain.request().newBuilder()
                            .addHeader(
                                "X-Naver-Client-Id",
                                API_ID
                            )
                            .addHeader(
                                "X-Naver-Client-Secret",
                                API_SECRET
                            )
                            .build()
                    )

                }
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ContentsService::class.java)


    override fun getContents(
        type: String,
        query: String,
        callback: NaverRemoteDataSource.Callback
    ) {
        retrofit.getContentsInfo(
            type = type,
            query = query
        ).enqueue(object : Callback<Content> {
            override fun onFailure(call: Call<Content>, t: Throwable) {
                with(t) {
                    if (BuildConfig.DEBUG) {
                        Log.d("RetroFit", "onFailure $message")
                    }

                    callback.onFailure(throwable = this)
                }
            }

            override fun onResponse(
                call: Call<Content>,
                response: Response<Content>
            ) {
                if (response.isSuccessful) {

                    response.body()?.let {
                        callback.onSuccess(it.items)
                    } ?: callback.onFailure(Throwable())

                } else {
                    callback.onFailure(Throwable())
                }
            }
        })
    }

    private const val API_ID = "N6fr8OFPNCzX7SVctnkG"
    private const val API_SECRET = "WHmQ8WtbYf"
}