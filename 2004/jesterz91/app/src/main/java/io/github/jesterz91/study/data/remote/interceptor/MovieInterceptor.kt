package io.github.jesterz91.study.data.remote.interceptor

import io.github.jesterz91.study.BuildConfig
import io.github.jesterz91.study.presentation.constant.Constant
import okhttp3.Interceptor
import okhttp3.Response

class MovieInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val requestUrl = "${chain.request().url}"

        if (requestUrl.startsWith(BuildConfig.BASE_URL)) {
            request.addHeader(Constant.HEADER_NAVER_CLIENT_ID, BuildConfig.NAVER_CLIENT_ID)
            request.addHeader(Constant.HEADER_NAVER_CLIENT_SECRET, BuildConfig.NAVER_CLIENT_SECRET)
        }
        return chain.proceed(request.build())
    }
}