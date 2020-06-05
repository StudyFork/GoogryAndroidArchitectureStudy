package io.github.jesterz91.study.data.remote.source

import io.github.jesterz91.study.data.remote.api.MovieApi
import io.github.jesterz91.study.data.remote.model.MovieRemote
import io.github.jesterz91.study.data.remote.model.MovieSearchResponse
import io.reactivex.Single

class MovieRemoteDataSourceImpl(private val movieApi: MovieApi) : MovieRemoteDataSource {

    override fun requestMovieInfo(query: String): Single<List<MovieRemote>> {
        return movieApi
            .searchMovie(query)
            .map(MovieSearchResponse::items)
    }
}