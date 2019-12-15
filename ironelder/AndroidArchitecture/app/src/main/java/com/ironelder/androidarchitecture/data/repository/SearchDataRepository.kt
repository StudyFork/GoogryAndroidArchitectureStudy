package com.ironelder.androidarchitecture.data.repository

import android.content.Context
import com.ironelder.androidarchitecture.data.SearchResult
import com.ironelder.androidarchitecture.data.TotalModel
import io.reactivex.Single

interface SearchDataRepository {

    fun getDataForSearchToRemote(
        type: String,
        query: String?
    ):Single<TotalModel>

    fun getDataForSearchToLocal(
        context: Context,
        type: String
    ):Single<SearchResult>?

}