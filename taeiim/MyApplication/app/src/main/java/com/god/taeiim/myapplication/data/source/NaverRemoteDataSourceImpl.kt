package com.god.taeiim.myapplication.data.source

import com.god.taeiim.myapplication.api.model.SearchResult
import com.god.taeiim.myapplication.api.provideAuthApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object NaverRemoteDataSourceImpl : NaverDataSource {
    override fun getResultData(
        searchType: String,
        query: String,
        success: (results: SearchResult) -> Unit,
        fail: (t: Throwable) -> Unit
    ) {
        provideAuthApi().searchContents(searchType, query).let {
            it.enqueue(object : Callback<SearchResult> {
                override fun onResponse(
                    call: Call<SearchResult>,
                    response: Response<SearchResult>
                ) {
                    response.body()?.let { result -> success(result) } ?: fail(Throwable())
                }

                override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                    fail(t)
                }
            })
        }
    }
}