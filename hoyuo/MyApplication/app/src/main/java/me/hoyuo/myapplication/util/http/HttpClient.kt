package me.hoyuo.myapplication.util.http

import com.facebook.stetho.okhttp3.StethoInterceptor
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import me.hoyuo.myapplication.BuildConfig
import me.hoyuo.myapplication.model.upbit.Market
import me.hoyuo.myapplication.model.upbit.Ticker
import me.hoyuo.myapplication.util.ContextHelper
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


object HttpClient {
    private val TAG = HttpClient::class.java.simpleName
    private const val URL = "https://api.upbit.com/"

    private lateinit var instance: HttpClient
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var httpInterface: HttpInterface

    init {
        updateHttpClient()
    }

    fun newInstance(): HttpClient {
        if (!::instance.isInitialized) {
            instance = HttpClient
        }
        return instance
    }

    private fun updateHttpClient() {
        okHttpClient = getOkHttpClient()
        httpInterface = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(HttpInterface::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient {
        val logInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        val httpCacheDirectory = File(ContextHelper.context.cacheDir, "http")
        val cacheSize = 32 * 1024 * 1024L

        val client = OkHttpClient.Builder()
            .cache(Cache(httpCacheDirectory, cacheSize))
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(logInterceptor)
            .addInterceptor(StethoInterceptor())
            .build()

        client.dispatcher().maxRequests = 16

        return client
    }

    fun getMarketList(): Flowable<List<Market>> {
        return httpInterface.getMarketList()
    }

    fun getTickers(list: String): Flowable<List<Ticker>> {
        return httpInterface.getTickers(list)
            .repeatWhen { t -> t.delay(10, TimeUnit.SECONDS) }
    }
}
