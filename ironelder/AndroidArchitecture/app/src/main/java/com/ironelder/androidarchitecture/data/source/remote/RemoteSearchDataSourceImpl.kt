package com.ironelder.androidarchitecture.data.source.remote

import com.ironelder.androidarchitecture.data.RetrofitForNaver
import com.ironelder.androidarchitecture.data.TotalModel
import io.reactivex.Single

object RemoteSearchDataSourceImpl :
    RemoteSearchDataSource {

    override fun getDataForSearch(type: String, query: String?): Single<TotalModel> {
        return RetrofitForNaver.searchApi.requestSearchForNaverWithAdapter(type, query)
    }

}