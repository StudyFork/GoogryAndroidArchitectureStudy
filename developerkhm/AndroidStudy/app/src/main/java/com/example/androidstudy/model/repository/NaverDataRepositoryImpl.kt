package com.example.androidstudy.model.repository

import com.example.androidstudy.api.data.TotalModel
import com.example.androidstudy.model.data.NaverRemoteDataImpl
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object NaverDataRepositoryImpl : NaverDataRepository {
    override fun getNaverSearchData(
        type: String,
        query: String
    ): Single<TotalModel> {
       return NaverRemoteDataImpl.callAPiNaverSearch(type, query)
    }
}
