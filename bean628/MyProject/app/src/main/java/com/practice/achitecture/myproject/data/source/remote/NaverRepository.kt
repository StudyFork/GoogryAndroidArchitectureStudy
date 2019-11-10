package com.practice.achitecture.myproject.data.source.remote

import com.practice.achitecture.myproject.network.RetrofitClient
import common.NAVER_API_BASE_URL

object NaverRepository : NaverRemoteDataSource {

    private val naverRemoteDataSource = NaverRemoteDataSourceImpl(
        RetrofitClient(
            NAVER_API_BASE_URL
        ).makeRetrofitServiceForNaver()
    )

    override fun searchWordByNaver(
        category: String,
        word: String,
        callBack: NaverRemoteDataSource.GettingResultOfSearchingCallBack
    ) {
        naverRemoteDataSource.searchWordByNaver(category, word, callBack)
    }


}