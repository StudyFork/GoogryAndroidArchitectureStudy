package com.lllccww.studyforkproject.data.source.remote

import com.lllccww.studyforkproject.SearchRetrofit
import com.lllccww.studyforkproject.data.model.Movie
import com.lllccww.studyforkproject.data.model.MovieItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRemoteDataSourceImpl : MovieRemoteDataSource {

    override fun getMovieList(
        query: String,
        onSuccess: (List<MovieItem>) -> Unit,
        onFailure: (e: Throwable) -> Unit
    ) {

        //영화정보 요청
        SearchRetrofit.getService().listMovie(keyword = query)
            .enqueue(object : Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    if(response.isSuccessful){
                        val movieItems = response.body()?.items?:return
                        onSuccess(movieItems!!)
                    }


                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    onFailure(t)
                }

            })
    }
}


