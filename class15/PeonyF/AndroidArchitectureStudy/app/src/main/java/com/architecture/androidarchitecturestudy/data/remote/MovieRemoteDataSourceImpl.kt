package com.architecture.androidarchitecturestudy.data.remote

import com.architecture.androidarchitecturestudy.data.model.MovieResponse
import com.architecture.androidarchitecturestudy.webservice.ApiClient
import com.architecture.androidarchitecturestudy.webservice.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRemoteDataSourceImpl(
    private val network: NetworkService = ApiClient.NETWORK_SERVICE
) : MovieRemoteDataSource {
    override fun getMovieData(
        keyword: String,
        display: Int,
        onSuccess: (MovieResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        network.getMovieSearch(keyword, display)
            .enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    val body = response.body()
                    body?.let {
                        if (it.items!!.isNotEmpty() && response.isSuccessful) {
                            onSuccess(it)
                        } else {
                            onEmptyList()
                        }
                    }
                }
            })
    }
}

