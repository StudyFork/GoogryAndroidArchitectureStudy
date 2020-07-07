package com.hyper.hyapplication.source.remote

import com.hyper.hyapplication.NaverRetrofit
import com.hyper.hyapplication.model.ResultGetSearchMovie
import com.hyper.hyapplication.ui.CLIENT_ID
import com.hyper.hyapplication.ui.CLIENT_SECRET
import retrofit2.Response

class NaverRemoteDataSourceImpl : NaverRemoteDataSource {

    override fun movieSearch(
        query: String,
        success: (List<ResultGetSearchMovie.Item>) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        NaverRetrofit.service.getSearchMovie(
            clientId = CLIENT_ID,
            clientSecret = CLIENT_SECRET,
            query = query
        ).enqueue(object : retrofit2.Callback<ResultGetSearchMovie> {
            override fun onResponse(
                call: retrofit2.Call<ResultGetSearchMovie>,
                response: Response<ResultGetSearchMovie>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        success(it.items)
                    }
                }
            }

            override fun onFailure(call: retrofit2.Call<ResultGetSearchMovie>, t: Throwable) {
                failure(t)
            }
        })
    }
}
