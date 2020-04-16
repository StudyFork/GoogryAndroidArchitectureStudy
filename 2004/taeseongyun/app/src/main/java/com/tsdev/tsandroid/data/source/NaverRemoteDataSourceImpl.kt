package com.tsdev.tsandroid.data.source

import com.tsdev.tsandroid.api.NaverAPI
import com.tsdev.tsandroid.data.MovieResponse
import io.reactivex.rxjava3.core.Single

class NaverRemoteDataSourceImpl : NaverRemoteDataSource {
    override fun getMovieList(query: String): Single<MovieResponse> =
        NaverAPI.movieAPI.getSearchMovie(query)
}