package com.egiwon.architecturestudy.data.source.remote

import android.util.Log
import com.egiwon.architecturestudy.BuildConfig
import com.egiwon.architecturestudy.data.Content
import com.egiwon.architecturestudy.data.source.service.RetrofitApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object NaverRemoteDataSourceImpl :
    NaverRemoteDataSource {

    override fun getContents(
        type: String,
        query: String,
        callback: NaverRemoteDataSource.Callback
    ) {
        RetrofitApi.retrofit.getContentsInfo(
            type = type,
            query = query
        ).enqueue(object : Callback<Content> {
            override fun onFailure(call: Call<Content>, t: Throwable) {
                with(t) {
                    if (BuildConfig.DEBUG) {
                        Log.d("RetroFit", "onFailure $message")
                    }

                    callback.onFailure(throwable = this)
                }
            }

            override fun onResponse(
                call: Call<Content>,
                response: Response<Content>
            ) {
                if (response.isSuccessful) {

                    response.body()?.let {
                        callback.onSuccess(it.items)
                    } ?: callback.onFailure(Throwable())

                } else {
                    callback.onFailure(Throwable())
                }
            }
        })
    }

}