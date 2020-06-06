package com.tsdev.tsandroid.data.source

import com.tsdev.tsandroid.data.MovieResponse
import com.tsdev.tsandroid.network.NaverMovieInterface
import io.reactivex.rxjava3.core.Single

class NaverRemoteDataSourceImpl(private val movieAPI: NaverMovieInterface) : NaverRemoteDataSource {
    override fun getMovieList(query: String): Single<MovieResponse> =
        movieAPI.getSearchMovie(query)
}