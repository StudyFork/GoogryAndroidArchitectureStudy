package com.tsdev.data.source.remote

import com.tsdev.data.source.MovieResponse
import io.reactivex.rxjava3.core.Single

interface NaverMovieRemoteSourceData {
    fun getMovieList(query: String): Single<MovieResponse>
}