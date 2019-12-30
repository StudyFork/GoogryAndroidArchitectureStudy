package com.example.androidstudy.model.data

import com.example.androidstudy.api.RetrofitBuilder
import com.example.androidstudy.api.data.TotalModel
import io.reactivex.Single

object NaverRemoteDataImpl : NaverRemoteData {
    override fun callAPiNaverSearch(type: String, query: String): Single<TotalModel> {
        return RetrofitBuilder.instance().requestSearchForNaver(type, query)
    }
}
