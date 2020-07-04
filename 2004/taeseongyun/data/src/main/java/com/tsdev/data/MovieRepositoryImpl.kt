package com.tsdev.data

import com.tsdev.data.model.MovieResponse
import com.tsdev.data.source.NaverMovieRemoteSourceData
import com.tsdev.domain.mapper.Mapper
import com.tsdev.domain.model.Item
import com.tsdev.domain.repository.NaverMovieRepository
import io.reactivex.rxjava3.core.Single

internal class MovieRepositoryImpl(
    private val naverMovieRemoteSourceData: NaverMovieRemoteSourceData,
    private val naverMapper: Mapper<MovieResponse, List<Item>>
) : NaverMovieRepository {
    override fun getMovieList(query: String): Single<List<Item>> {
        return naverMovieRemoteSourceData.getMovieList(query).map {
            naverMapper.toData(it)
        }
    }
}