package com.hyper.hyapplication.source.remote

import com.hyper.hyapplication.NaverRetrofit
import com.hyper.hyapplication.model.ResultGetSearchMovie
import retrofit2.Response

const val CLIENT_ID = "ImN25OL4axIIwuH6jXDj"
const val CLIENT_SECRET = "vysJ6N1WXx"

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
