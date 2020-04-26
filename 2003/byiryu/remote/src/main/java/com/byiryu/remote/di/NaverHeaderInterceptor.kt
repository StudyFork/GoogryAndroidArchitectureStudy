package com.byiryu.remote.di

import com.byiryu.remote.conf.RemoteConf
import okhttp3.Interceptor
import okhttp3.Response


class NaverHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("X-Naver-Client-Id", RemoteConf.NAVER_CLIENT_ID)
            .addHeader("X-Naver-Client-Secret", RemoteConf.NAVER_CLIENT_SECRET)
            .build()
        return chain.proceed(request)
    }

}
