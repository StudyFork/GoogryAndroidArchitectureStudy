package com.tsdev.data

import com.tsdev.data.model.EntryItem
import com.tsdev.data.source.NaverMovieRemoteSourceData
import com.tsdev.domain.mapper.Mapper
import com.tsdev.domain.model.DomainItem
import com.tsdev.domain.repository.NaverMovieRepository
import io.reactivex.rxjava3.core.Single

internal class MovieRepositoryImpl(
    private val naverMovieRemoteSourceData: NaverMovieRemoteSourceData,
    private val naverMapper: Mapper<EntryItem, List<DomainItem>>
) : NaverMovieRepository {
    override fun getMovieList(query: String): Single<List<DomainItem>> {
        return naverMovieRemoteSourceData.getMovieList(query).map {
            naverMapper.toData(it)
        }
    }
}