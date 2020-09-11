package com.example.myproject.data.source.remote

import com.example.myproject.data.model.Items
import com.example.myproject.data.model.Movie
import com.example.myproject.data.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NaverRemoteDataSourceImpl : NaverRemoteDataSource {
    override fun getMovieList(
        title: String,
        success: (ArrayList<Items>) -> Unit,
        failed: (String) -> Unit
    ) {
        RetrofitClient.getService().getMovies(title).enqueue(object : Callback<Movie> {
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                failed(t.message.toString())
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        success(it.items)
                    }
                } else {
                    failed(response.message())
                }
            }
        })
    }
}
