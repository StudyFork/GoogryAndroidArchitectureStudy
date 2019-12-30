package com.example.androidstudy.model.repository

import com.example.androidstudy.model.data.TotalModel
import io.reactivex.Single

interface NaverDataRepository {
    fun getNaverSearchData(
        type: String,
        query: String
    ): Single<TotalModel>
}