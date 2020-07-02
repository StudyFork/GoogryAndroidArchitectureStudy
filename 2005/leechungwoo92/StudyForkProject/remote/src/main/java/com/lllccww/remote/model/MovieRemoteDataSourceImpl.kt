package com.lllccww.remote.model

import com.lllccww.data.source.remote.MovieRemoteDataSource
import com.lllccww.remote.ApiService
import com.lllccww.remote.MovieMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRemoteDataSourceImpl(private val api: ApiService) : MovieRemoteDataSource {



    override fun getMovieList(
        keyWord: String,
        onSuccess: (List<com.lllccww.data.model.MovieItem>) -> Unit,
        onFailure: (e: Throwable) -> Unit
    ) {

        //영화정보 요청
        api.listMovie(keyword = keyWord)
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    if (response.isSuccessful) {
                        val movieItems = response.body()?.items ?: return
                        onSuccess(MovieMapper.remoteToData(movieItems))
                    }


                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    onFailure(t)
                }

            })
    }


}


