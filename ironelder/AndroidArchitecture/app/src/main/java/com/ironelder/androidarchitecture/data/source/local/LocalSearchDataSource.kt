package com.ironelder.androidarchitecture.data.source.local

import android.content.Context
import com.ironelder.androidarchitecture.data.SearchResult
import io.reactivex.Single

interface LocalSearchDataSource {

    fun getLocalSearchData(context: Context, type: String): Single<SearchResult>?

    fun setLocalSearchData(
        context: Context,
        searchResult: SearchResult
    )

}