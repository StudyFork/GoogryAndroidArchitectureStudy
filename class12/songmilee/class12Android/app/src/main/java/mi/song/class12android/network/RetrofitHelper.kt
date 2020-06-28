package mi.song.class12android.network

import android.content.Context
import mi.song.class12android.R
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {
    companion object {
        private const val BASE_URL = "https://openapi.naver.com/v1/search/"
        private var retrofitService:Retrofit? = null

        fun getService(context:Context) : MovieService {
            if (retrofitService == null){
                val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
                    val newRequest = chain.request().newBuilder()
                        .addHeader("X-Naver-Client-Id", context.getString(R.string.naver_client_id))
                        .addHeader("X-Naver-Client-Secret", context.getString(R.string.naver_client_secret))
                        .build()

                    chain.proceed(newRequest)
                }.build()

                retrofitService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofitService!!.create(MovieService::class.java)
        }
    }
}