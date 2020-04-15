package io.github.jesterz91.study.data.remote.source

import io.github.jesterz91.study.data.remote.MovieService
import io.github.jesterz91.study.data.remote.model.MovieRemote
import io.github.jesterz91.study.data.remote.model.MovieSearchResponse
import io.reactivex.Single

class MovieRemoteDataSourceImpl : MovieRemoteDataSource {

    override fun requestMovie(query: String): Single<List<MovieRemote>> {
        return MovieService.movieApi
            .searchMovie(query)
            .map(MovieSearchResponse::items)
    }
}