package com.example.archstudy.data.source.remote

import com.example.archstudy.BuildConfig
import com.example.archstudy.Item
import com.example.archstudy.MovieData
import com.example.archstudy.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NaverQueryRemoteDataSourceImpl : NaverQueryRemoteDataSource {

    override fun getMovie(query: String): List<Item> {

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_URI)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(RetrofitService::class.java).apply {
            this.getMovieList(
                BuildConfig.API_CLIENT_ID,
                BuildConfig.API_CLIENT_SECRET,
                query
            )
                .enqueue(object : Callback<MovieData> {

                    override fun onFailure(call: Call<MovieData>, t: Throwable) {
                        println(t.printStackTrace())
                    }

                    override fun onResponse(
                        call: Call<MovieData>,
                        response: Response<MovieData>
                    ) {

                        with(response) {

                            if (isSuccessful && body() != null) {
                                val updateList = body()!!.items

                            }
                        }
                    }
                })
        }
    }
}