package com.example.androidstudy.model.data

import com.example.androidstudy.api.data.TotalModel
import io.reactivex.Single

interface NaverRemoteData {
    fun callAPiNaverSearch(type: String, query: String): Single<TotalModel>
}