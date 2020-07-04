package com.tsdev.remote

import com.tsdev.data.model.EntryItem
import com.tsdev.data.source.NaverMovieRemoteSourceData
import com.tsdev.remote.model.RemoteItem
import com.tsdev.remote.network.NaverMovieAPI
import com.tsdev.remote.network.mapper.Mapper
import io.reactivex.rxjava3.core.Single

internal class NaverMovieRemoteDataSourceImpl(
    private val naverMovieAPI: NaverMovieAPI,
    private val mapper: Mapper<EntryItem, RemoteItem>
) : NaverMovieRemoteSourceData {

    override fun getMovieList(query: String): Single<List<EntryItem>> {
        return naverMovieAPI.getSearchMovie(query)
            .map {
                it.items
            }
            .map { it.map (mapper::toData)  }

    }
}