package com.hyper.hyapplication.source.remote

import com.hyper.hyapplication.model.ResultGetSearchMovie
import com.hyper.hyapplication.source.NaverAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val CLIENT_ID = "ImN25OL4axIIwuH6jXDj"
const val CLIENT_SECRET = "vysJ6N1WXx"
const val BASE_URL = "https://openapi.naver.com"

class NaverRemoteDataSourceImpl(private val naverApi: NaverAPI) :
    NaverRemoteDataSource {

    override fun movieSearch(
        query: String,
        success: (List<ResultGetSearchMovie.Item>) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        naverApi.getSearchMovie(
            clientId = CLIENT_ID,
            clientSecret = CLIENT_SECRET,
            query = query
        ).enqueue(object : Callback<ResultGetSearchMovie> {
            override fun onResponse(
                call: Call<ResultGetSearchMovie>,
                response: Response<ResultGetSearchMovie>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        success(it.items)
                    }
                }
            }

            override fun onFailure(call: Call<ResultGetSearchMovie>, t: Throwable) {
                failure(t)
            }
        })
    }
}
