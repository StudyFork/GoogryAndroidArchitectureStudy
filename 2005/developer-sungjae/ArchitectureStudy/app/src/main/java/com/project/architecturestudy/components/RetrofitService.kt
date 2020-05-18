package com.project.architecturestudy.components

import com.project.architecturestudy.BuildConfig
import com.project.architecturestudy.components.Constants.NAVER_SEARCH_BASE_URL
import com.project.architecturestudy.data.model.Movie
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("v1/search/movie.json")
    fun getMovies(@Query("query") title: String): Single<Movie>

    companion object ServiceFactory {
        fun create(): RetrofitService {

            val builder = OkHttpClient.Builder()

            if (BuildConfig.DEBUG) {
                val interceptor =
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                builder.addInterceptor(interceptor)
            }

            builder.addInterceptor {
                val request =
                    it.request().newBuilder()
                        .addHeader("X-Naver-Client-Id", BuildConfig.NAVER_CLIENT_ID)
                        .addHeader("X-Naver-Client-Secret", BuildConfig.NAVER_CLIENT_SECRET)
                        .build()
                it.proceed(request)
            }

            val client = builder.build()

            val services = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(NAVER_SEARCH_BASE_URL)
                .client(client)
                .build()
            return services.create(RetrofitService::class.java)
        }
    }
}