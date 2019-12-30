package com.example.androidstudy.model.source

import android.content.Context
import com.example.androidstudy.database.SearchResultDatabase
import com.example.androidstudy.model.data.SearchResultEntity
import io.reactivex.Single

object NaverLocalDataSourceImpl : NaverLocalDataSource{
    override fun getLocalSearchData(
        context: Context,
        type: String
    ): Single<SearchResultEntity>? {
        return SearchResultDatabase.getInstance(context)?.SearchResultDao()
            ?.getLastSearchResult(type)
    }

    override fun setLocalSearchData(
        context: Context,
        searchResult: SearchResultEntity
    ) {
        SearchResultDatabase.getInstance(context)?.SearchResultDao()
            ?.insertSearchResult(searchResult)
    }

    fun getLocalSearchDataBase(context: Context): SearchResultDatabase?{
        return SearchResultDatabase.getInstance(context)
    }
}