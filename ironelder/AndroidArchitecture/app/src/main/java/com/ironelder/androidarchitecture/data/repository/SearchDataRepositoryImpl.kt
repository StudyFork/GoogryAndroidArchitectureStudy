package com.ironelder.androidarchitecture.data.repository

import com.ironelder.androidarchitecture.data.RetrofitForNaver
import com.ironelder.androidarchitecture.data.TotalModel
import io.reactivex.Single
import retrofit2.Call

object SearchDataRepositoryImpl : SearchDataRepository {
    override fun getDataForSearch(type: String, query: String): Call<TotalModel> {
        return RetrofitForNaver.searchApi.requestSearchForNaver(type, query)
    }


    override fun getDataForSearchWithAdapter(type: String, query: String): Single<TotalModel> {
        return RetrofitForNaver.searchApi.requestSearchForNaverWithAdapter(type, query)
    }
}