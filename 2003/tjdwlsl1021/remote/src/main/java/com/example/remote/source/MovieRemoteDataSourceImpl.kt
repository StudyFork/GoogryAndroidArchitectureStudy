package com.example.remote.source

import com.example.data2.model.Movie
import com.example.data2.soruce.remote.MovieRemoteDataSource
import com.example.remote.model.MovieResponse
import com.example.remote.model.api.NaverMovieApi
import com.example.remote.model.mapper.remoteToData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRemoteDataSourceImpl(private val naverMovieApi: NaverMovieApi) :
    MovieRemoteDataSource {

    override fun getMovieList(
        query: String,
        success: (List<Movie>) -> Unit,
        failed: (Throwable) -> Unit
    ) {
        val call = naverMovieApi.getMovieList(query, 10, 1, "1")

        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()

                    result?.let {
                        success(remoteToData(result.items))
                    }
                    if (result == null) {
                        failed(Throwable("오류"))
                    }
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                failed(t)
            }
        })
    }
}