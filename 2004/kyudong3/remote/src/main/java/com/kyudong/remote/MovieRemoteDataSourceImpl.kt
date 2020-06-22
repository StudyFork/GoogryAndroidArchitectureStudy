package com.kyudong.remote

import com.kyudong.data.model.Movie
import com.kyudong.data.remote.MovieRemoteDataSource
import com.kyudong.remote.mapper.MovieRemoteMapper
import com.kyudong.remote.network.MovieReceiver
import com.kyudong.remote.network.NaverApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

internal class MovieRemoteDataSourceImpl(
    private val movieRemoteMapper: MovieRemoteMapper,
    private val naverApiService: NaverApiService
) : MovieRemoteDataSource {

    override fun getMovieList(
        query: String,
        success: (List<Movie>) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        naverApiService.getSearchMovie(movieName = query)
            .enqueue(object : Callback<MovieReceiver> {
                override fun onResponse(
                    call: Call<MovieReceiver>,
                    response: Response<MovieReceiver>
                ) {
                    val body = response.body()
                    if (body != null && response.isSuccessful) {
                        success(movieRemoteMapper.toDomain(body.items))
                    } else {
                        failure(HttpException(response))
                    }
                }

                override fun onFailure(
                    call: Call<MovieReceiver>,
                    t: Throwable
                ) {
                    failure(t)
                }
            })
    }
}