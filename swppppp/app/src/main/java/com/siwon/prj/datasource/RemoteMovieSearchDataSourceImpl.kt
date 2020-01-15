package com.siwon.prj.datasource

import com.siwon.prj.datasource.service.MovieSearchServiceImpl
import com.siwon.prj.model.ApiInfo
import com.siwon.prj.model.Movie
import com.siwon.prj.model.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteMovieSearchDataSourceImpl(/*private val service: MovieSearchService*/) : RemoteMovieSearchDataSource {

    override fun searchMovies(
        query: String,
        success: (ArrayList<Movie>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        MovieSearchServiceImpl.service.search(ApiInfo.CLIENT_ID, ApiInfo.CLIENT_SECRET, query)
            .enqueue(object : Callback<Movies> {
                override fun onFailure(call: Call<Movies>, t: Throwable) {
                    fail(t)
                }

                override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                    success(response.body()!!.movies)
                }
            })
    }
}
