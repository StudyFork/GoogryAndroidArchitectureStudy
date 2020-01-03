package com.example.androidstudy.model.source

import com.example.androidstudy.api.RetrofitBuilder
import com.example.androidstudy.model.data.TotalModel
import io.reactivex.Single

object NaverRemoteDataSourceImpl : NaverRemoteDataSource {
    override fun callAPiNaverSearch(type: String, query: String): Single<TotalModel> {
        return RetrofitBuilder.instance().requestSearchForNaver(type, query)
    }
}
