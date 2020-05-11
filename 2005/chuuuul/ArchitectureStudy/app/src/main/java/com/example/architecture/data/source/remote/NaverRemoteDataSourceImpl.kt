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

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ConstValue.MOVIE_SEARCH_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun getMovieList(
        keyWord: String,
        onSuccess: (movies: List<MovieModel>) -> Unit,
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

                movieResponse?.also {
                    onSuccess(it.movies)
                }
            }

            override fun onFailure(call: Call<MovieResponseModel>, t: Throwable) {
                onFailure(t)
            }
        })
    }
}