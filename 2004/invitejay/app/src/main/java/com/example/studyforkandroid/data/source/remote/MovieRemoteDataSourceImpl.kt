package com.example.studyforkandroid.data.source.remote

import com.example.studyforkandroid.data.Movie
import com.example.studyforkandroid.data.MovieResponse
import com.example.studyforkandroid.network.MovieApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class MovieRemoteDataSourceImpl(
    private val api: MovieApi
) : MovieRemoteDataSource {

    override fun getMovieList(
        query: String,
        onSuccess: (List<Movie>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        api.movieRequest(query).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val movieItem = response.body()
                    if (movieItem != null) {
                        onSuccess(movieItem.movie)
                    }
                } else {
                    onFailure(HttpException(response))
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                onFailure(t)
            }
        })
    }
}