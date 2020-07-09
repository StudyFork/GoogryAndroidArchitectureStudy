package com.example.study.data.repository

import com.example.study.data.model.NaverApiData
import com.example.study.data.remote.RemoteService
import com.example.study.view.clientId
import com.example.study.view.clientSecret
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListRepositoryImpl : MovieListRepository {
    override fun doSearch(
        query: String,
        response: (List<NaverApiData.Item>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        RemoteService.movieApiService.getSearch(
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