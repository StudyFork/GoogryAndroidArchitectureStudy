package app.ch.study.di

import app.ch.study.data.remote.api.WebApiDefine
import app.ch.study.data.remote.api.WebApiDefine.Companion.CONNECT_TIMEOUT
import app.ch.study.data.remote.api.WebApiDefine.Companion.READ_TIMEOUT
import app.ch.study.data.remote.api.WebApiDefine.Companion.WRITE_TIMEOUT
import app.ch.study.data.remote.api.WebApiParams
import app.ch.study.data.remote.parse.StudyRequestInterceptor
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit

val networkModule = module {
    factory {
        WebApiParams(androidContext())
    }

    //serverUrl
    factory {
        WebApiDefine.HOST_IP(androidContext()) ?: ""
    }

    //request
    factory {
        StudyRequestInterceptor(get())
    }

    //okHttp
    factory {
        provideOkHttpClient(get())
    }

    //retrofit
    factory {
        provideRetrofit(get(), get())
    }
}

fun provideOkHttpClient(
    requestInterceptor: StudyRequestInterceptor
): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    val cookie = CookieManager()
    cookie.setCookiePolicy(CookiePolicy.ACCEPT_ALL)

    return OkHttpClient().newBuilder()
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS) //연결 타임아웃 시간 설정
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS) //쓰기 타임아웃 시간 설정
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS) //읽기 타임아웃 시간 설정
        .cookieJar(JavaNetCookieJar(cookie))
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(requestInterceptor)
        .build()
}

fun provideRetrofit(serverUrl: String, okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(serverUrl)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
