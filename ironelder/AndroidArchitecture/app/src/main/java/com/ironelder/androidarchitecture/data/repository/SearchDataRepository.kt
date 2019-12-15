package com.ironelder.androidarchitecture.data.repository

import android.content.Context
import com.ironelder.androidarchitecture.data.ResultItem
import com.ironelder.androidarchitecture.data.SearchResult
import com.ironelder.androidarchitecture.data.TotalModel
import io.reactivex.Single

interface SearchDataRepository {

    fun getRemoteSearchData(
        type: String,
        query: String?
    ): Single<TotalModel>

    fun getLocalSearchData(
        context: Context,
        type: String
    ): Single<SearchResult>?

    fun setLocalSearchData(
        context: Context,
        type: String,
        searchWord: String,
        items: ArrayList<ResultItem>
    )

}