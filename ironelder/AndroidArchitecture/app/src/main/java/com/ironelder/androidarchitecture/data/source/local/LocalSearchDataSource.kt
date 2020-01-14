package com.ironelder.androidarchitecture.data.source.local

import com.ironelder.androidarchitecture.data.SearchResult
import io.reactivex.Single

interface LocalSearchDataSource {

    fun getLocalSearchData(type: String): Single<SearchResult>?

    fun setLocalSearchData(
        searchResult: SearchResult
    )

}