package m.woong.architecturestudy.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import m.woong.architecturestudy.App
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkConnectionInterceptor : Interceptor {

    private val applicationContext = App.appContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable()) throw IOException("Make sure you have an active data connection")
        val request = chain.request().newBuilder()
            .addHeader("X-Naver-Client-Id", CLIENT_ID)
            .addHeader("X-Naver-Client-Secret", CLIENT_SECRET)
            .build()
        return chain.proceed(request)
    }

    private fun isInternetAvailable(): Boolean {
        var result = false
        val connectivityManager =
            applicationContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.let {
            it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
        }
        return result
    }

    companion object {
        const val CLIENT_ID = "lHrslPhzC2bFPf8LZ4kB"
        const val CLIENT_SECRET = "0vJ3mpzX6_"
    }
}