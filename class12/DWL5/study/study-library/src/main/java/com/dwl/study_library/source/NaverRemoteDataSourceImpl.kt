package com.dwl.study_library.source

import com.dwl.study_library.model.Movie
import com.dwl.study_library.service.MovieApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NaverRemoteDataSourceImpl @Inject constructor(private val remoteService: MovieApi) :
    NaverRemoteDataSource {


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