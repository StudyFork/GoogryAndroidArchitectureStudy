package com.tsdev.tsandroid.data.source

import com.tsdev.tsandroid.NaverMovieInterface
import com.tsdev.tsandroid.data.MovieResponse
import io.reactivex.rxjava3.core.Single

class NaverRemoteDataSourceImpl(private val naverAPI: NaverMovieInterface) : NaverRemoteDataSource {
    override fun getMovieList(query: String): Single<MovieResponse> =
        naverAPI.getSearchMovie(query)

}