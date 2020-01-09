package com.ironelder.androidarchitecture.data.source.local

import com.ironelder.androidarchitecture.data.SearchResult
import com.ironelder.androidarchitecture.data.dao.SearchResultDao
import io.reactivex.Single

interface LocalSearchDataSource {

    fun getLocalSearchData(searchResultDao: SearchResultDao, type: String): Single<SearchResult>?

    fun setLocalSearchData(
        searchResultDao: SearchResultDao, searchResult: SearchResult
    )

}