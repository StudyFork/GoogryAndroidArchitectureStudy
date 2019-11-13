package com.example.androidstudy.model.repository

import com.example.androidstudy.api.data.TotalModel
import com.example.androidstudy.model.data.NaverRemoteDataImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object NaverDataRepositoryImpl : NaverDataRepository {
    override fun getNaverSearchData(
        type: String,
        query: String,
        success: (result: TotalModel) -> Unit,
        fail: (msg: String) -> Unit
    ) {
        NaverRemoteDataImpl.callAPiNaverSearch(type, query).enqueue(object : Callback<TotalModel> {
            override fun onFailure(call: Call<TotalModel>, t: Throwable) {
                t.message?.let {
                    fail(it)
                }
            }

            override fun onResponse(call: Call<TotalModel>, response: Response<TotalModel>) {
                response.body()?.let {
                    success(it)
                }
            }
        })
    }
}
