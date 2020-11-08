package com.showmiso.architecturestudy.data.repository

import com.showmiso.architecturestudy.api.MovieModel
import io.reactivex.Single

interface NaverRepository {
    fun getMovies(query: String): Single<MovieModel.MovieResponse>
}