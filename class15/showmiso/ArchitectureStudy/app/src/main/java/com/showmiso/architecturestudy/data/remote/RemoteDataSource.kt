package com.showmiso.architecturestudy.data.remote

import com.showmiso.architecturestudy.api.MovieModel
import io.reactivex.Single

interface RemoteDataSource {
    fun getMovies(
        query: String
    ): Single<MovieModel.MovieResponse>
}