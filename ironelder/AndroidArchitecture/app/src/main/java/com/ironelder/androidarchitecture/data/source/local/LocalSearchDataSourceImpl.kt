package com.ironelder.androidarchitecture.data.source.local

import com.ironelder.androidarchitecture.data.SearchResult
import com.ironelder.androidarchitecture.data.database.SearchResultDatabase
import io.reactivex.Single

class LocalSearchDataSourceImpl(private val searchResultDatabase: SearchResultDatabase) :
    LocalSearchDataSource {

    override fun getLocalSearchData(
        type: String
    ): Single<SearchResult>? {
        return searchResultDatabase.searchResultDao()
            .getLastSearchResult(type)
    }

    override fun setLocalSearchData(
        searchResult: SearchResult
    ) {
        searchResultDatabase.searchResultDao()
            .insertSearchResult(searchResult)
    }

}