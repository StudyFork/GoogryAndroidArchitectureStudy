package com.byiryu.remote

import com.byiryu.data.model.Response
import com.byiryu.data.source.remote.RemoteDataSource
import com.byiryu.remote.model.apis.Apis
import com.byiryu.remote.model.mapper.MovieMapper
import io.reactivex.Single

internal class RemoteDataSourceImpl(private val apis: Apis) : RemoteDataSource{
    override fun getMoveList(query: String): Single<Response> {
        return apis.getSearchMovie(query).map {
            MovieMapper.remoteToData(it)
        }
    }

}