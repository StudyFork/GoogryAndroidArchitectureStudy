package com.ironelder.androidarchitecture.data.source

import com.ironelder.androidarchitecture.data.TotalModel
import com.ironelder.androidarchitecture.data.repository.SearchDataRepoImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object SearchDataSrcImpl : ISearchDataSrc<TotalModel, String> {
    override fun getDataForSearch(
        type: String,
        query: String,
        result:ISearchDataSrc.RequestCallback
    ) {
        SearchDataRepoImpl.getDataForSearch(type, query).enqueue(object : Callback<TotalModel> {
            override fun onFailure(call: Call<TotalModel>, t: Throwable) {
                t.message?.let { result.onFail(it) }
            }

            override fun onResponse(call: Call<TotalModel>, response: Response<TotalModel>) {
                response.body()?.let{result.onSuccess(it)}
            }

        })
    }
}