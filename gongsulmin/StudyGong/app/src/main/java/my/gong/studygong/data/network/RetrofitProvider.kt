package my.gong.studygong.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {
    fun upbitRetrofit(): UpbitApi{
        return Retrofit.Builder()
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
            .create(UpbitApi::class.java)
    }
}