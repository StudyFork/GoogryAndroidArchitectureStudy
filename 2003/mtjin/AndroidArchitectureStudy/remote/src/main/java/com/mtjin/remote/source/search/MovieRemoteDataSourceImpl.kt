package com.mtjin.remote.source.search

import android.util.Log
import com.mtjin.data.source.search.remote.MovieRemoteDataSource
import com.mtjin.remote.api.ApiInterface
import com.mtjin.remote.mapper.mapperMovieListRemoteToData
import com.mtjin.remote.model.search.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response


internal class MovieRemoteDataSourceImpl(private val apiInterface: ApiInterface) :
    MovieRemoteDataSource {

    override fun getSearchMovies(
        query: String,
        start: Int,
        success: (List<com.mtjin.data.model.search.Movie>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        val movieCall = apiInterface.getSearchMovie(query, start)
        movieCall.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d(TAG, "Remote onFailure")
                fail(t)
            }

            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                with(response) {
                    val body = body()
                    if (isSuccessful && body != null) {
                        Log.d(TAG, "Remote onResponse success")
                        if (start != body.start) {
                            fail(Throwable("마지막 페이지"))
                        } else {
                            success(mapperMovieListRemoteToData(body.movies))
                        }
                    } else {
                        Log.d(TAG, "Remote onResponse fail")
                        fail(HttpException(this))
                    }
                }
            }
        })
    }

    companion object {
        const val TAG = "MovieRemoteDataSource"
    }
}