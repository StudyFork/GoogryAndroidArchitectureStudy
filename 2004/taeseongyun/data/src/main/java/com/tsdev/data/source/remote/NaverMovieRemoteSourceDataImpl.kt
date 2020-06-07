package com.tsdev.data.source.remote

import com.tsdev.data.source.MovieResponse
import com.tsdev.data.source.network.NaverMovieAPI
import io.reactivex.rxjava3.core.Single

internal class NaverMovieRemoteSourceDataImpl(private val naverAPI: NaverMovieAPI) :
    NaverMovieRemoteSourceData {
    override fun getMovieList(query: String): Single<MovieResponse> {
        return naverAPI.getSearchMovie(query)
    }

}