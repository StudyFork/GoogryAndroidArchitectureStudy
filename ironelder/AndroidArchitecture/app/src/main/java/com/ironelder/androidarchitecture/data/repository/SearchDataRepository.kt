package com.ironelder.androidarchitecture.data.repository

import com.ironelder.androidarchitecture.data.SearchResult
import com.ironelder.androidarchitecture.data.TotalModel
import com.ironelder.androidarchitecture.data.dao.SearchResultDao
import io.reactivex.Single

interface SearchDataRepository {

    fun getRemoteSearchData(
        type: String,
        query: String,
        searchResultDao: SearchResultDao
    ): Single<TotalModel>

    fun getLocalSearchData(
        type: String,
        searchResultDao: SearchResultDao
    ): Single<SearchResult>?

}