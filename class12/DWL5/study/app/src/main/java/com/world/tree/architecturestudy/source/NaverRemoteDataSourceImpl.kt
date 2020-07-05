package com.world.tree.architecturestudy.source

import com.world.tree.architecturestudy.RetrofitService
import com.world.tree.architecturestudy.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NaverRemoteDataSourceImpl(val remoteService: RetrofitService) : NaverRemoteDataSource {


    override fun getMovies(
        q: String,
        success: (List<Movie.Item>) -> Unit,
        error: (Throwable) -> Unit
    ) {
        remoteService.requestSearchMovie(query = q).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                response.body()?.let {
                    success(it.items)
                }
            }
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                error(t)
            }
        })
    }
}