package com.tsdev.domain.repository

import com.tsdev.data.source.Item
import com.tsdev.data.source.MovieResponse
import com.tsdev.data.source.remote.NaverMovieRemoteSourceData
import com.tsdev.domain.mapper.Mapper
import io.reactivex.rxjava3.core.Single

internal class NaverMovieRepositoryImpl(
    private val naverMovieRemoteSourceData: NaverMovieRemoteSourceData,
    private val mapper: Mapper<MovieResponse, List<Item>>
) :
    NaverMovieRepository {
    override fun getMovieList(query: String): Single<List<Item>> {
        return naverMovieRemoteSourceData.getMovieList(query).map {
            mapper.toData(it)
        }
    }

}