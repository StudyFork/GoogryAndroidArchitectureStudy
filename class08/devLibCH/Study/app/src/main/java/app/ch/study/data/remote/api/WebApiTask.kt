package app.ch.study.data.remote.api

import app.ch.study.BuildConfig
import app.ch.study.data.remote.api.WebApiDefine.CONNECT_TIMEOUT
import app.ch.study.data.remote.api.WebApiDefine.READ_TIMEOUT
import app.ch.study.data.remote.api.WebApiDefine.WRITE_TIMEOUT
import app.ch.study.data.remote.parse.StudyRequestInterceptor
import com.google.gson.Gson
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit

object WebApiTask {

    private var apiInterface: WebApi? = null

    fun getInstance(): WebApi {
        if (apiInterface == null) {
            val params = WebApiParams()

            val requestInterceptor = StudyRequestInterceptor(params)

            val cookieManager = cookie()

            //OkHttpClient 생성
            val builder: OkHttpClient.Builder = OkHttpClient().newBuilder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS) //연결 타임아웃 시간 설정
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS) //쓰기 타임아웃 시간 설정
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS) //읽기 타임아웃 시간 설정
                .cookieJar(JavaNetCookieJar(cookieManager))
                .addInterceptor(requestInterceptor)

            if (BuildConfig.DEBUG) {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

                builder.addInterceptor(httpLoggingInterceptor)
            }

            val client = builder.build()

            val baseUrl = WebApiDefine.HOST_IP()
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()

            apiInterface = retrofit.create(WebApi::class.java)
        }

        return apiInterface!!
    }

    private fun cookie(): CookieManager {
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)

        return cookieManager
    }

}