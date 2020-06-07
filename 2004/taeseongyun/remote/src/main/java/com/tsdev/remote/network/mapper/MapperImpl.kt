package com.tsdev.remote.network.mapper

import com.tsdev.data.source.MovieResponse
import com.tsdev.remote.source.MovieDomainResponse
import io.reactivex.rxjava3.core.Single

class MapperImpl : Mapper<MovieResponse, MovieDomainResponse> {
    override fun toData(data: Single<MovieResponse>): Single<MovieDomainResponse> {
        return data.map {
            MovieDomainResponse(
                it.display,
                it.items,
                it.lastBuildDate,
                it.start,
                it.total
            )
        }
    }

    override fun fromData(data: Single<MovieDomainResponse>): Single<MovieResponse> {
        return data.map {
            MovieResponse(
                it.display,
                it.items,
                it.lastBuildDate,
                it.start,
                it.total
            )
        }
    }

}