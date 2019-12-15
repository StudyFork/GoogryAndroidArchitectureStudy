package com.ironelder.androidarchitecture.data.source.local

import android.content.Context
import com.ironelder.androidarchitecture.data.SearchResult
import com.ironelder.androidarchitecture.data.database.SearchResultDatabase
import io.reactivex.Single

object LocalSearchDataSourceImpl :
    LocalSearchDataSource {
    override fun getLocalDataForSearch(
        context: Context,
        type: String
    ): Single<SearchResult>? {
        return SearchResultDatabase.getInstance(context)?.SearchResultDao()?.getLastSearchResult(type)
    }
}