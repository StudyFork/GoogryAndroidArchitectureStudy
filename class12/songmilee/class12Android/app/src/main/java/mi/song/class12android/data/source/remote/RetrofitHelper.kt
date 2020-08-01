package mi.song.class12android.data.source.remote

import android.content.Context
import mi.song.class12android.R
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object RetrofitHelper {

    private const val BASE_URL = "https://openapi.naver.com/v1/search/"
    private var retrofitService: Retrofit? = null

    fun getService(context: Context): MovieService {
        if (retrofitService == null) {
            val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("X-Naver-Client-Id", context.getString(R.string.naver_client_id))
                    .addHeader(
                        "X-Naver-Client-Secret",
                        context.getString(R.string.naver_client_secret)
                    )
                    .build()
                chain.proceed(newRequest)
            }
                .initSsl()
                .build()

            retrofitService = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofitService!!.create(MovieService::class.java)
    }

    private fun OkHttpClient.Builder.initSsl(): OkHttpClient.Builder {
        try {
            val trustAllcerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun checkClientTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?
                ) {
                }

                override fun checkServerTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?
                ) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            })

            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllcerts, SecureRandom())

            this.sslSocketFactory(sslContext.socketFactory, trustAllcerts[0] as X509TrustManager)

            return this
        } catch (e: Exception) {
            e.printStackTrace()
            return this
        }
    }

}