package my.gong.studygong.data.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 싱글톤 변경
object RetrofitProvider {

    val upbitApi: UpbitApi by lazy {
        val retrofitBuiilder = Retrofit.Builder()
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
            return@lazy retrofitBuiilder.create(UpbitApi::class.java)
    }

    // 싱글톤 아님
//    fun upbitRetrofit(): UpbitApi{
//        return Retrofit.Builder()
//            .baseUrl(UpbitApi.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(
//                OkHttpClient.Builder().addInterceptor(
//                        HttpLoggingInterceptor()
//                            .apply {
//                                level = HttpLoggingInterceptor.Level.BODY
//                            }
//                    )
//                    .build()
//            )
//            .build()
//            .create(UpbitApi::class.java)
//    }
}