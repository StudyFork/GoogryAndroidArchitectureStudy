package com.example.architecturestudy.data.repository

import com.example.architecturestudy.data.model.MovieData
import com.example.architecturestudy.data.model.MovieMeta
import com.example.architecturestudy.data.source.remote.MovieRemoteService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRespositoryImpl : MovieRepository {
    override fun remoteSearchMovie(
        movieTitle: String,
        success: (List<MovieData>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        MovieRemoteService.movieApiService.searchMovie(movieTitle).enqueue(object : Callback<MovieMeta> {
            override fun onFailure(call: Call<MovieMeta>, t: Throwable) {
                fail(t)
            }

            override fun onResponse(
                call: Call<MovieMeta>,
                response: Response<MovieMeta>
            ) {
                response.body()?.items?.let { success(it) }
            }

        })
    }
}