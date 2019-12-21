package com.example.architecturestudy.data.source.remote

import com.example.architecturestudy.data.model.MovieData
import com.example.architecturestudy.data.model.MovieItems
import com.example.architecturestudy.network.Api
import com.example.architecturestudy.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NaverSearchRemoteDataSourceImpl : NaverSearchRemoteDataSource {

    val restClient: Api = ApiClient.getRetrofitService(Api::class.java)


    override fun getMovie(keyword: String, success: (MovieData) -> Unit, fail: Unit) {
        restClient.requestMovies(keyword).enqueue(object : Callback<MovieData> {
            override fun onFailure(call: Call<MovieData>, t: Throwable) {
                error(message = t.toString())
            }

            override fun onResponse(call: Call<MovieData>, response: Response<MovieData>) {
                if(response.isSuccessful) {
                    response.body()?.items?.let {
                        success(MovieData(it))
                    }
                }
            }
        })
    }
}