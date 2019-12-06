package com.example.androidarchitecture.apis

import okhttp3.Dns
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.UnknownHostException

object NetworkUtil {
    private val SAFE_DNS: Dns by lazy {
        Dns { hostname ->
            try {
                return@Dns Dns.SYSTEM.lookup(hostname)
            } catch (e: IllegalArgumentException) {
                // Hack. See https://github.com/square/okhttp/issues/3345
                throw UnknownHostException(e.message)
            } catch (e: NullPointerException) {
                // Hack. See https://github.com/square/okhttp/issues/3345
                throw UnknownHostException(e.message)
            } catch (e: Exception) {
                // Hack. See https://github.com/square/okhttp/issues/3345
                throw UnknownHostException(e.message)
            }
        }
    }
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .dns(SAFE_DNS)
            .addInterceptor(AppInterceptor())
            .build()
    }

    fun getRetrofit(baseUrl: String): Retrofit {
        return try {
            Retrofit.Builder().client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        } catch (e: Exception) {
            e.printStackTrace()
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    // 인터셉터.
    class AppInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val mRequest = chain.request()
            val newRequest = mRequest.newBuilder()
                .addHeader("X-Naver-Client-Id", "Cjj07G06ms2sCWlKWezF")
                .addHeader("X-Naver-Client-Secret", "4v_hXcCSrz")
                .build()
            return chain.proceed(newRequest)
        }
    }

}