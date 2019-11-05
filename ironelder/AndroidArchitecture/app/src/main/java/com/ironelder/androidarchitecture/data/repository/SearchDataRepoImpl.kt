package com.ironelder.androidarchitecture.data.repository

import com.ironelder.androidarchitecture.data.RetrofitForNaver
import com.ironelder.androidarchitecture.data.TotalModel
import retrofit2.Call

object SearchDataRepoImpl : SearchDataRepository<TotalModel> {
    override fun getDataForSearch(type: String, query: String): Call<TotalModel> {
        return RetrofitForNaver.searchApi.requestSearchForNaver(type, query)
    }
}