package com.example.androidarchitecture.apis

import okhttp3.Dns
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.UnknownHostException

class NetworkUtil {

    companion object {

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
                .addInterceptor(appInterceptor())
                .build()
        }

        fun getRetrofit(baseUrl: String, factory: Converter.Factory?): Retrofit {
            var retrofit: Retrofit
            try {
                if (factory == null) {
                    retrofit = Retrofit.Builder().client(okHttpClient)
                        .baseUrl(baseUrl)
                        .build()
                } else {
                    retrofit = Retrofit.Builder().client(okHttpClient)
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                if (factory == null) {
                    retrofit = Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .build()
                } else {
                    retrofit = Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }

            return retrofit
        }

        // 인터셉터.
        class appInterceptor : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val mRequest = chain.request()
                val newmequest = mRequest.newBuilder()
                    .addHeader("X-Naver-Client-Id", "Cjj07G06ms2sCWlKWezF")
                    .addHeader("X-Naver-Client-Secret", "4v_hXcCSrz")
                    .build()
                return chain.proceed(newmequest)
            }
        }
    }

}