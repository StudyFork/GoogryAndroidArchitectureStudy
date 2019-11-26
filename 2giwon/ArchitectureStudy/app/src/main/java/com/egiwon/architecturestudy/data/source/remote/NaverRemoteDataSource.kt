package com.egiwon.architecturestudy.data.source.remote

import android.util.Log
import com.egiwon.architecturestudy.BuildConfig
import com.egiwon.architecturestudy.data.Content
import com.egiwon.architecturestudy.data.source.NaverDataSource
import com.egiwon.architecturestudy.data.source.service.RetrofitApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NaverRemoteDataSource : NaverDataSource {

    override fun getContents(
        type: String,
        query: String,
        onSuccess: (resultList: List<Content.Item>) -> Unit,
        onFailure: (throwable: Throwable) -> Unit
    ) {
        RetrofitApi.retrofit.getContentsInfo(
            type = type,
            query = query
        ).enqueue(object : Callback<Content> {

            override fun onFailure(call: Call<Content>, t: Throwable) {
                t.let {
                    if (BuildConfig.DEBUG) {
                        Log.d("RetroFit", "onFailure ${it.message}")
                    }

                    onFailure(it)
                }
            }

            override fun onResponse(
                call: Call<Content>,
                response: Response<Content>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        onSuccess(
                            response.body()!!.items
                        )

                    } else {
                        onFailure(Throwable())
                    }

                } else {
                    onFailure(Throwable())
                }
            }
        })
    }

    companion object {
        private var instance: NaverRemoteDataSource? = null

        fun getInstance() = instance ?: NaverRemoteDataSource()
            .apply { instance = this }
    }
}