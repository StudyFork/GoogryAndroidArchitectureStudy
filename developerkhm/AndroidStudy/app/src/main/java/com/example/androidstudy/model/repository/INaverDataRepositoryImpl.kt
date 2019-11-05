package com.example.androidstudy.model.repository

import com.example.androidstudy.api.data.TotalModel
import com.example.androidstudy.model.data.NaverDataImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object INaverDataRepositoryImpl : INaverDataRepository{
    override fun getNaverSearchData(
        type: String,
        query: String,
        success: (result : TotalModel) -> Unit,
        fail: (msg: String) -> Unit
    ) {
        NaverDataImpl.callAPiNaverSearch(type, query).enqueue(object : Callback<TotalModel> {
            override fun onFailure(call: Call<TotalModel>, t: Throwable) {
                t.message?.let{
                    fail(it)    //message 에서 ?하면 it이 message 인가?? 헷갈림..
                }
            }
            override fun onResponse(call: Call<TotalModel>, response: Response<TotalModel>) {
                response.body()?.let{
                    success(it)
                }
            }
        })
    }
}
