package com.example.androidstudy.model.source

import android.content.Context
import com.example.androidstudy.model.data.SearchResultEntity
import io.reactivex.Single

interface NaverLocalDataSource {
    fun getLocalSearchData(context: Context, type: String): Single<SearchResultEntity>?

    fun setLocalSearchData(
        context: Context,
        searchResult: SearchResultEntity
    )
}