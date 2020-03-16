package app.ch.study.di

import app.ch.study.BuildConfig
import app.ch.study.data.remote.api.WebApiDefine
import app.ch.study.data.remote.api.WebApiParams
import app.ch.study.data.remote.parse.StudyRequestInterceptor
import com.google.gson.Gson
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit

val networkModule = module {

    single {
        WebApiParams()
    }

    //serverUrl
    single {
        WebApiDefine.HOST_IP()
    }

    //request
    single {
        StudyRequestInterceptor(get())
    }

    //okHttp
    single {
        provideOkHttpClient(get())
    }

    //gson
    single {
        provideGson()
    }

    //retrofit
    single {
        provideRetrofit(get(), get(), get())
    }

}

fun provideOkHttpClient(requestInterceptor: StudyRequestInterceptor) : OkHttpClient {
    val cookieManager = CookieManager()
    cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)

    val builder: OkHttpClient.Builder = OkHttpClient().newBuilder()
        .connectTimeout(WebApiDefine.CONNECT_TIMEOUT, TimeUnit.SECONDS) //연결 타임아웃 시간 설정
        .writeTimeout(WebApiDefine.WRITE_TIMEOUT, TimeUnit.SECONDS) //쓰기 타임아웃 시간 설정
        .readTimeout(WebApiDefine.READ_TIMEOUT, TimeUnit.SECONDS) //읽기 타임아웃 시간 설정
        .cookieJar(JavaNetCookieJar(cookieManager))
        .addInterceptor(requestInterceptor)

    if (BuildConfig.DEBUG) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        builder.addInterceptor(httpLoggingInterceptor)
    }

    return builder.build()
}

fun provideGson(): GsonConverterFactory = GsonConverterFactory.create(Gson())

fun provideRetrofit(serverUrl: String, okHttpClient: OkHttpClient, gson: GsonConverterFactory): Retrofit =
    Retrofit.Builder()
        .baseUrl(serverUrl)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(gson)
        .build()