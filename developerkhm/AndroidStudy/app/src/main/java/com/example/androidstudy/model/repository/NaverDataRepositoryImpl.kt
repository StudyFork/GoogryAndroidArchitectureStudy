package com.example.androidstudy.model.repository

import com.example.androidstudy.model.data.TotalModel
import com.example.androidstudy.model.source.NaverRemoteDataSourceImpl
import io.reactivex.Single

object NaverDataRepositoryImpl : NaverDataRepository {
    override fun getNaverSearchData(
        type: String,
        query: String
    ): Single<TotalModel> {
       return NaverRemoteDataSourceImpl.callAPiNaverSearch(type, query)
    }
}
