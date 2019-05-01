package me.hoyuo.myapplication.util.http

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import io.reactivex.Single
import me.hoyuo.myapplication.BuildConfig
import me.hoyuo.myapplication.model.upbit.Market
import me.hoyuo.myapplication.model.upbit.Ticker
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


public class HttpClient private constructor(context: Context) {
    init {
        updateHttpClient(context)
    }

    private fun updateHttpClient(context: Context) {
        okHttpClient = getOkHttpClient(context)
        httpInterface = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
                .create(HttpInterface::class.java)
    }

    private fun getOkHttpClient(context: Context): OkHttpClient {
        val logInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        val httpCacheDirectory = File(context.cacheDir, "http")
        val cacheSize = 32 * 1024 * 1024L

        var client = OkHttpClient.Builder()
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

    fun getMarketList(): Single<List<Market>> {
        return httpInterface.getMarketList()
    }

    fun getTickers(list: String): Single<List<Ticker>> {
        return httpInterface.getTickers(list)
    }

    companion object {
        @JvmStatic
        fun getInstance(context: Context): HttpClient {
            if (!::instance.isInitialized) {
                instance = HttpClient(context)
            }
            return instance
        }

        private val TAG = HttpClient::class.java.simpleName
        private const val URL = "https://api.upbit.com/"

        private lateinit var instance: HttpClient
        private lateinit var okHttpClient: OkHttpClient
        private lateinit var httpInterface: HttpInterface
    }
}