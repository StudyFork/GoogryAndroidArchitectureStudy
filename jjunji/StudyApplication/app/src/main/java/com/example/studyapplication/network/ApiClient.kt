package com.example.studyapplication.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    companion object {
        private const val BASE_URL = "https://openapi.naver.com/v1"
        private var retrofit : Retrofit? = null

        fun getService() : IService {
            return getClient()!!.create(IService::class.java)
        }

        private fun getClient() : Retrofit? {
            if(retrofit == null) {
                synchronized(this) {
                    if(retrofit == null) {
                        retrofit = Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .client(okHttpClient())
                            .addConverterFactory(GsonConverterFactory.create(gson()))
                            .build()
                    }
                }
            }
            return retrofit
        }

        private fun gson() : Gson {
            return GsonBuilder().setLenient().create()
        }

        private fun okHttpClient() : OkHttpClient {
            return OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(headerInterceptor())
                .build()
        }

        private fun headerInterceptor() : Interceptor {
            return object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request().newBuilder()
                        .addHeader("X-Naver-Client-Id", "eNLy_J4hwzGVWUIldEXT")
                        .addHeader("X-Naver-Client-Secret", "SONPvb_Alx")
                        .build()
                    return chain.proceed(request)
                }
            }
        }
    }
}