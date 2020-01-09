package com.ironelder.androidarchitecture.data.source.local

import com.ironelder.androidarchitecture.data.SearchResult
import com.ironelder.androidarchitecture.data.dao.SearchResultDao
import io.reactivex.Single

object LocalSearchDataSourceImpl :
    LocalSearchDataSource {

    override fun getLocalSearchData(
        searchResultDao: SearchResultDao,
        type: String
    ): Single<SearchResult>? {
        return searchResultDao
            .getLastSearchResult(type)
    }

    override fun setLocalSearchData(
        searchResultDao: SearchResultDao,
        searchResult: SearchResult
    ) {
        searchResultDao
            .insertSearchResult(searchResult)
    }

}