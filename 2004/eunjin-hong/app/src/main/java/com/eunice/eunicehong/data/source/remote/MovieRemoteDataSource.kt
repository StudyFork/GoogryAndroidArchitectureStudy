package com.eunice.eunicehong.data.source.remote

import com.eunice.eunicehong.data.model.MovieList
import com.eunice.eunicehong.data.source.MovieDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class MovieRemoteDataSource : MovieDataSource {
    private val service = MovieClient.getClient().create(MovieService::class.java)

    override fun getMovieList(query: String, callback: MovieDataSource.LoadMoviesCallback) {
        service.getMovieList(query).enqueue(object : Callback<MovieList> {
            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                val content = response.body()
                if (response.isSuccessful && content != null) {
                    callback.onSuccess(content)
                } else {
                    callback.onFailure(HttpException(response))
                }
            }

            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }
}