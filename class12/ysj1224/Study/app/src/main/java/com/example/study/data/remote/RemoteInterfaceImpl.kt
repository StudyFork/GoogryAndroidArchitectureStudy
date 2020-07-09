package com.example.study.data.remote

import com.example.study.data.api.NaverApi
import com.example.study.data.model.NaverApiData
import com.example.study.view.clientId
import com.example.study.view.clientSecret
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteInterfaceImpl: RemoteInterface {
    override fun getSearch(
        query: String,
        response: (List<NaverApiData.Item>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        NaverApi.NaverRetrofit.SERVICE.getSearch(
            clientId = clientId,
            clientPw = clientSecret,
            query = query
        ).enqueue(object : Callback<NaverApiData> {
            override fun onFailure(call: Call<NaverApiData>, t: Throwable) {
                fail(t)
            }
            override fun onResponse(call: Call<NaverApiData>, response: Response<NaverApiData>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        response(it.items)
                    }
                }
            }
        })
    }
}