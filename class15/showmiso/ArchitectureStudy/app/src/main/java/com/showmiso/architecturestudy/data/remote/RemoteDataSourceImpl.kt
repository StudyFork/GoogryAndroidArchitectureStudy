package com.showmiso.architecturestudy.data.remote

import com.showmiso.architecturestudy.api.MovieModel
import io.reactivex.Single

class RemoteDataSourceImpl : RemoteDataSource {
    override fun getMovies(query: String): Single<MovieModel.MovieResponse> {
        TODO("Not yet implemented")
    }

}