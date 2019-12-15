package com.ironelder.androidarchitecture.data.source.remote

import com.ironelder.androidarchitecture.data.TotalModel
import io.reactivex.Single

interface RemoteSearchDataSource {

    fun getDataForSearch(type: String, query: String?): Single<TotalModel>

}