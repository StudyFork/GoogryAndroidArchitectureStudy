package com.example.androidarchitecturestudy.data.datasource

import com.example.androidarchitecturestudy.data.GetMovieInfo
import com.example.androidarchitecturestudy.retrofit.RetrofitClient
import com.example.androidarchitecturestudy.retrofit.ServerIp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRemoteDataSourceImpl : MovieRemoteDataSource {

    override fun getMovieSearchResult(
        movieName: String,
        onSuccess: (GetMovieInfo.MovieList) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {

        RetrofitClient(ServerIp.naverMovieApiUrl).apiService.getMovieSearchResult(movieName)
            .enqueue(object : Callback<GetMovieInfo.MovieList> {

                override fun onResponse(
                    call: Call<GetMovieInfo.MovieList>,
                    response: Response<GetMovieInfo.MovieList>
                ) {

                    response.body()?.let {
                        onSuccess(it)
                    }

                }

                override fun onFailure(call: Call<GetMovieInfo.MovieList>, t: Throwable) {
                    onFailure(t)

                }
            });

    }
}