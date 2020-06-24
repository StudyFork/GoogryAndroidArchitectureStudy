package com.example.architecture.data.source.remote

import com.example.architecture.data.model.MovieModel
import com.example.architecture.data.model.MovieResponseModel
import com.example.architecture.retrofit.MovieSearchService
import com.example.architecture.util.ConstValue
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NaverRemoteDataSourceImpl : NaverRemoteDataSource {

    override val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ConstValue.MOVIE_SEARCH_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun getMovieList(
        keyWord: String,
        onSuccess: (movieList: List<MovieModel>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {

        val service = retrofit.create(MovieSearchService::class.java)
        val call = service.requestSearchMovie(keyWord)
        call.enqueue(object : Callback<MovieResponseModel> {

            override fun onResponse(
                call: Call<MovieResponseModel>,
                response: Response<MovieResponseModel>
            ) {
                val movieResponse = response.body()

                movieResponse?.movieList?.also {
                    onSuccess(it)
                }
            }

            override fun onFailure(call: Call<MovieResponseModel>, t: Throwable) {
                onFailure(t)
            }
        })
    }
}