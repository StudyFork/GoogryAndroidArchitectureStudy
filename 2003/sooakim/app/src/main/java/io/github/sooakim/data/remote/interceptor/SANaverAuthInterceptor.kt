package io.github.sooakim.data.remote.interceptor

import io.github.sooakim.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class SANaverAuthInterceptor : Interceptor, SAInterceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val requestUrl = chain.request().url.toString()

        if (requestUrl.startsWith(BuildConfig.NAVER_BASE_URL)) {
            request.addHeader(HEADER_NAVER_AUTH_CLIENT_ID, BuildConfig.NAVER_CLIENT_ID)
            request.addHeader(HEADER_NAVER_AUTH_CLIENT_SECRET, BuildConfig.NAVER_CLIENT_SECRET)
        }
        return chain.proceed(request.build())
    }

    companion object {
        private const val HEADER_NAVER_AUTH_CLIENT_ID = "X-Naver-Client-Id"
        private const val HEADER_NAVER_AUTH_CLIENT_SECRET = "X-Naver-Client-Secret"
    }
}