package app.ch.study.data.remote.parse

import app.ch.study.data.remote.api.WebApiParams
import okhttp3.Interceptor
import okhttp3.Response

class StudyRequestInterceptor(private val webApiParams: WebApiParams) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val token = webApiParams.getClientToken()?:""
        val secret  = webApiParams.getClientSecret()?:""

        val requestBuilder = original.newBuilder()
                .header("X-Naver-Client-Id", token)
                .header("X-Naver-Client-Secret", secret)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}