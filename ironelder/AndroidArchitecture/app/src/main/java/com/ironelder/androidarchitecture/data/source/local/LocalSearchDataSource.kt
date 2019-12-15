package com.ironelder.androidarchitecture.data.source.local

import android.content.Context
import com.ironelder.androidarchitecture.data.SearchResult
import io.reactivex.Single

interface LocalSearchDataSource {

    fun getLocalDataForSearch(context: Context, type: String): Single<SearchResult>?

}