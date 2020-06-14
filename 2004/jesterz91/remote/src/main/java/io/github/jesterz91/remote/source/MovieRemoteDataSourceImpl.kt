package io.github.jesterz91.remote.source

import io.github.jesterz91.data.model.MovieEntity
import io.github.jesterz91.data.source.MovieRemoteDataSource
import io.github.jesterz91.remote.api.MovieApi
import io.github.jesterz91.remote.mapper.MovieRemoteMapper
import io.github.jesterz91.remote.model.MovieSearchResponse
import io.reactivex.Single

class MovieRemoteDataSourceImpl(private val movieApi: MovieApi) : MovieRemoteDataSource {

    override fun requestMovieInfo(query: String): Single<List<MovieEntity>> {
        return movieApi
            .searchMovie(query)
            .map(MovieSearchResponse::items)
            .map { it.map(MovieRemoteMapper::mapToEntity) }
    }
}