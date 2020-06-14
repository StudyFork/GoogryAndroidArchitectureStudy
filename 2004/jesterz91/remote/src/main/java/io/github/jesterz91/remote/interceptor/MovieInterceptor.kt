package io.github.jesterz91.remote.interceptor

import io.github.jesterz91.remote.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class MovieInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val requestUrl = "${chain.request().url}"

        if (requestUrl.startsWith(BuildConfig.BASE_URL)) {
            request.addHeader(HEADER_NAVER_CLIENT_ID, BuildConfig.NAVER_CLIENT_ID)
            request.addHeader(HEADER_NAVER_CLIENT_SECRET, BuildConfig.NAVER_CLIENT_SECRET)
        }
        return chain.proceed(request.build())
    }

    companion object {
        const val HEADER_NAVER_CLIENT_ID = "X-Naver-Client-Id"
        const val HEADER_NAVER_CLIENT_SECRET = "X-Naver-Client-Secret"
    }
}