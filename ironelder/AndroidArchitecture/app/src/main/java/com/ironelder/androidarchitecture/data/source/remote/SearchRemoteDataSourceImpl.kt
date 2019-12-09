package com.ironelder.androidarchitecture.data.source.remote

import com.ironelder.androidarchitecture.data.RetrofitForNaver
import com.ironelder.androidarchitecture.data.TotalModel
import com.ironelder.androidarchitecture.data.source.SearchDataSource
import io.reactivex.Single

object SearchRemoteDataSourceImpl :
    SearchDataSource {

    override fun getDataForSearch(type: String, query: String?): Single<TotalModel> {
        return RetrofitForNaver.searchApi.requestSearchForNaverWithAdapter(type, query)
    }

}