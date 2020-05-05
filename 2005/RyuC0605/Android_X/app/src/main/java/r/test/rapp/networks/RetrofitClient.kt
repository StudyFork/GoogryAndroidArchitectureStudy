package r.test.rapp.networks

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import r.test.rapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val TIME_OUT_SEC = 10

    fun getClient(baseUrl: String?): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(genOkHttpClient())
            .build()
    }

    private fun genOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
            .connectTimeout(TIME_OUT_SEC.toLong(), TimeUnit.SECONDS)  // 클라이언트 연결 타임아웃 10초
            .readTimeout(TIME_OUT_SEC.toLong(), TimeUnit.SECONDS)     //  read 타임아웃 10초
            .writeTimeout(TIME_OUT_SEC.toLong(), TimeUnit.SECONDS)    // write 타임아웃 10초
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }
}