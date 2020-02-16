package com.siwon.prj.common.remote.datasource

import com.siwon.prj.common.remote.datasource.service.MovieSearchService
import com.siwon.prj.common.model.ApiInfo
import com.siwon.prj.common.model.Movie
import com.siwon.prj.common.model.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteMovieSearchDataSourceImpl(private val service: MovieSearchService) : RemoteMovieSearchDataSource {

    override fun searchMovies(
        query: String,
        success: (ArrayList<Movie>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        service.search(ApiInfo.CLIENT_ID, ApiInfo.CLIENT_SECRET, query)
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
