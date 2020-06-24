package com.example.architecture.data.source.remote

import com.example.architecture.data.model.MovieModel
import com.example.architecture.data.model.MovieResponseModel
import com.example.architecture.retrofit.MovieSearchService
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NaverRemoteDataSourceImpl : NaverRemoteDataSource {

    private val movieSearchService by inject(MovieSearchService::class.java)

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