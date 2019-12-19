package com.ironelder.androidarchitecture.data.repository

import android.content.Context
import com.ironelder.androidarchitecture.data.ResultItem
import com.ironelder.androidarchitecture.data.SearchResult
import com.ironelder.androidarchitecture.data.TotalModel
import com.ironelder.androidarchitecture.data.database.SearchResultDatabase
import io.reactivex.Single

interface SearchDataRepository {

    fun getRemoteSearchData(
        type: String,
        query: String?,
        database: SearchResultDatabase?
    ): Single<TotalModel>

    fun getLocalSearchData(
        type: String,
        database: SearchResultDatabase?
    ): Single<SearchResult>?

}