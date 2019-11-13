package com.example.androidstudy.model.data

import com.example.androidstudy.api.RetrofitBuilder
import com.example.androidstudy.api.data.TotalModel
import retrofit2.Call

object NaverRemoteDataImpl : NaverRemoteData {
    override fun callAPiNaverSearch(type: String, query: String): Call<TotalModel> {
        return RetrofitBuilder.instance().requestSearchForNaver(type, query)
    }
}
