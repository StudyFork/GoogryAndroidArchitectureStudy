package com.ironelder.androidarchitecture.data.source.local

import android.content.Context
import com.ironelder.androidarchitecture.data.SearchResult
import com.ironelder.androidarchitecture.data.database.SearchResultDatabase
import io.reactivex.Single

object LocalSearchDataSourceImpl :
    LocalSearchDataSource {

    override fun getLocalSearchData(
        context: Context,
        type: String
    ): Single<SearchResult>? {
        return SearchResultDatabase.getInstance(context)?.SearchResultDao()
            ?.getLastSearchResult(type)
    }

    override fun setLocalSearchData(
        context: Context,
        searchResult: SearchResult
    ) {
        SearchResultDatabase.getInstance(context)?.SearchResultDao()
            ?.insertSearchResult(searchResult)
    }

    fun getLocalSearchDataBase(context: Context): SearchResultDatabase?{
        return SearchResultDatabase.getInstance(context)
    }

}