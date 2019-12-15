package com.ironelder.androidarchitecture.data.repository

import android.content.Context
import com.ironelder.androidarchitecture.data.SearchResult
import com.ironelder.androidarchitecture.data.TotalModel
import com.ironelder.androidarchitecture.data.source.local.LocalSearchDataSourceImpl
import com.ironelder.androidarchitecture.data.source.remote.RemoteSearchDataSourceImpl
import io.reactivex.Single

object SearchDataRepositoryImpl :
    SearchDataRepository {

    override fun getRemoteSearchData(
        type: String,
        query: String?
    ): Single<TotalModel> {
        return RemoteSearchDataSourceImpl.getDataForSearch(type, query)
    }

    override fun getLocalSearchData(context: Context, type: String): Single<SearchResult>? {
        return LocalSearchDataSourceImpl.getLocalDataForSearch(context, type)
    }

}