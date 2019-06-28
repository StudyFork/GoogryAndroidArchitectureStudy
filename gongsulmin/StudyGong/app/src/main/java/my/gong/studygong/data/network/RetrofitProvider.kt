package my.gong.studygong.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {
    val upbitApi: UpbitApi by lazy {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(UpbitApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder().addInterceptor(
                    HttpLoggingInterceptor()
                        .apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                )
                    .build()
            )
            .build()
        retrofitBuilder.create(UpbitApi::class.java)
    }
}