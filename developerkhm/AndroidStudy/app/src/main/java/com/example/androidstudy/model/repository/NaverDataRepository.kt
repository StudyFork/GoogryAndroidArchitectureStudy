package com.example.androidstudy.model.repository

import com.example.androidstudy.database.SearchResultDatabase
import com.example.androidstudy.model.data.SearchResultEntity
import com.example.androidstudy.model.data.TotalModel
import io.reactivex.Single

interface NaverDataRepository {
    fun getNaverSearchData(
        type: String,
        query: String
    ): Single<TotalModel>

    fun getLocalSearchData(
        type: String,
        database: SearchResultDatabase?
    ): Single<SearchResultEntity>?

    fun setLocalSearchData(

    )
}