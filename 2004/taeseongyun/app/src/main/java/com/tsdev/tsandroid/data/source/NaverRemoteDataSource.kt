package com.tsdev.tsandroid.data.source

import com.tsdev.tsandroid.data.MovieResponse
import io.reactivex.rxjava3.core.Single

interface NaverRemoteDataSource {
    fun getMovieList(query: String): Single<MovieResponse>
}