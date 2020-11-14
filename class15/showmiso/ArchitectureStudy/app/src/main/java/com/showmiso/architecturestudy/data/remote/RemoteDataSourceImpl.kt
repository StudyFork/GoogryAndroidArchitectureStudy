package com.showmiso.architecturestudy.data.remote

import com.showmiso.architecturestudy.Constants
import com.showmiso.architecturestudy.api.ApiInterface
import com.showmiso.architecturestudy.api.MovieModel
import com.showmiso.architecturestudy.api.RetrofitClient
import io.reactivex.Single

class RemoteDataSourceImpl : RemoteDataSource {
    private val api: ApiInterface by lazy {
        RetrofitClient.createService(
            Constants.MOVIE_URL,
            Constants.CLIENT_ID,
            Constants.CLIENT_SECRET
        )
    }

    override fun getMovies(query: String): Single<MovieModel.MovieResponse> {
        return api.getMovies(query)
    }
}