package com.tsdev.tsandroid.di

import com.tsdev.tsandroid.BuildConfig
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/***
 * single : 한번 선언되면 고유한 인스턴스를 유지.
 * factory : 호출 될 때마다 새로운 인스턴스를 생성.
 * viewModel : factory 와 비슷한거 같음. 동적 매개변수를 전달하려면 람다식으로 전달.
 * by viewModel : lazy 패턴으로 주입.  ViewModelStoreOwner 확장함수로 getViewModel 로 뷰 모델을 가져옴.
 ***/

private const val X_NAVER_CLIENT_ID = "X-Naver-Client-Id"
private const val X_NAVER_CLIENT_SECRET = "X-Naver-Client-Secret"
private const val REQUEST_TIME_OUT = 1000L

val networkModule = module {
    single {
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single {
        OkHttpClient.Builder().addInterceptor { get() }.addInterceptor { chain ->
            chain.proceed(chain.request().newBuilder().also {
                it.addHeader(X_NAVER_CLIENT_ID, BuildConfig.Client_ID)
                it.addHeader(X_NAVER_CLIENT_SECRET, BuildConfig.Client_SECRET)
            }.build())
        }
            .callTimeout(REQUEST_TIME_OUT, TimeUnit.MILLISECONDS)
            .readTimeout(REQUEST_TIME_OUT, TimeUnit.MILLISECONDS)
            .writeTimeout(REQUEST_TIME_OUT, TimeUnit.MILLISECONDS)
    }

    single {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }
}