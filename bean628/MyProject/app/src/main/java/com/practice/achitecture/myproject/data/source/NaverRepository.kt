package com.practice.achitecture.myproject.data.source

import com.practice.achitecture.myproject.data.source.remote.NaverRemoteDataSource
import com.practice.achitecture.myproject.data.source.remote.NaverRemoteDataSourceImpl

object NaverRepository : NaverRemoteDataSource {

    private val naverRemoteDataSourceImpl = NaverRemoteDataSourceImpl

    override fun searchWordByNaver(
        category: String,
        word: String,
        callBack: NaverRemoteDataSource.GetResultOfSearchingCallBack
    ) {
        naverRemoteDataSourceImpl.searchWordByNaver(category, word, callBack)
    }


}