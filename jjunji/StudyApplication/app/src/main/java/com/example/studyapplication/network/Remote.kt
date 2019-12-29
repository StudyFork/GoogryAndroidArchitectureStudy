package com.example.studyapplication.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Remote {
    fun <T> get(request: Call<T>, callback: Conn) {
        request.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    callback.success(response.body())
                } else {
                    callback.failed(response.message())
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                callback.failed(t.toString())
            }
        })
    }
}
