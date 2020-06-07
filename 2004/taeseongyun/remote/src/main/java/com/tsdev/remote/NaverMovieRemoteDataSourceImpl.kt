package com.tsdev.remote

import com.tsdev.data.source.MovieResponse
import com.tsdev.data.source.remote.NaverMovieRemoteSourceData
import com.tsdev.remote.network.NaverMovieAPI
import com.tsdev.remote.network.mapper.Mapper
import com.tsdev.remote.source.MovieDomainResponse
import io.reactivex.rxjava3.core.Single

internal class NaverMovieRemoteDataSourceImpl(
    private val naverMovieAPI: NaverMovieAPI,
    private val mapper: Mapper<MovieResponse, MovieDomainResponse>
) : NaverMovieRemoteSourceData {

    override fun getMovieList(query: String): Single<MovieResponse> {
        return mapper
            .fromData(naverMovieAPI.getSearchMovie(query))
    }
}