package com.byiryu.study.model.remote

import com.byiryu.study.conf.AppConf
import okhttp3.Interceptor
import okhttp3.Response


class NaverHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("X-Naver-Client-Id", AppConf.NAVER_CLIENT_ID)
            .addHeader("X-Naver-Client-Secret", AppConf.NAVER_CLIENT_SECRET)
            .build()
        return chain.proceed(request)
    }

}
