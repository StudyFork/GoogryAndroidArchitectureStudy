package com.example.kyudong3.data.remote

import com.example.kyudong3.data.model.Movie
import com.example.kyudong3.data.model.MovieReceiver
import com.example.kyudong3.network.NaverApiService
import com.example.kyudong3.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class MovieRemoteDataSourceImpl : MovieRemoteDataSource {
    private val naverService: NaverApiService = RetrofitClient.naverService

    override fun getMovieList(
        query: String,
        success: (List<Movie>) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        naverService.getSearchMovie(movieName = query)
            .enqueue(object : Callback<MovieReceiver> {
                override fun onResponse(
                    call: Call<MovieReceiver>,
                    response: Response<MovieReceiver>
                ) {
                    val body = response.body()
                    if (body != null && response.isSuccessful) {
                        success(body.items)
                    } else {
                        failure(HttpException(response))
                    }
                }

                override fun onFailure(call: Call<MovieReceiver>, t: Throwable) {
                    failure(t)
                }
            })
    }
}