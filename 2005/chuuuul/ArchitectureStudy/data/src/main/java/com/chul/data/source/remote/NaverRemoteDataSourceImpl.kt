package com.chul.data.source.remote

import com.chul.data.model.MovieModel
import com.chul.data.model.MovieResponseModel
import com.chul.data.retrofit.MovieSearchService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class NaverRemoteDataSourceImpl(private val movieSearchService: MovieSearchService) : NaverRemoteDataSource {

    override fun getMovieList(
        keyWord: String,
        onSuccess: (movieList: List<MovieModel>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {

        val call = movieSearchService.requestSearchMovie(keyWord)
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