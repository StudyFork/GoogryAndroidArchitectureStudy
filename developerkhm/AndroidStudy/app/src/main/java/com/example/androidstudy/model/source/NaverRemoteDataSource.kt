package com.example.androidstudy.model.source

import com.example.androidstudy.model.data.TotalModel
import io.reactivex.Single

interface NaverRemoteDataSource {
    fun callAPiNaverSearch(type: String, query: String): Single<TotalModel>
}