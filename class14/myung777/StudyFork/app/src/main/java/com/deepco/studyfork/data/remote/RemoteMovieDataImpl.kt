package com.deepco.studyfork.data.remote

import com.deepco.studyfork.api.RetrofitService
import com.deepco.studyfork.data.model.Item
import com.deepco.studyfork.data.model.MovieData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteMovieDataImpl : RemoteMovieData {
    private var api: RetrofitService = RetrofitService.create()
    override fun getMovieList(
        title: String,
        success: (List<Item>) -> Unit,
        failed: (String) -> Unit
    ) {
        api.getSearchMovie(title).enqueue(object : Callback<MovieData> {
            override fun onFailure(call: Call<MovieData>, t: Throwable) {
                failed(t.message.toString())
            }

            override fun onResponse(call: Call<MovieData>, response: Response<MovieData>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        if (it.items.isNotEmpty()) {
                            success(it.items)
                        } else {
                            failed("$title 를 찾을 수 없습니다")
                        }
                    }
                } else {
                    failed(response.message())
                }
            }
        })
    }
}