package io.github.jesterz91.data.source

import io.github.jesterz91.data.model.MovieEntity
import io.reactivex.Single

interface MovieRemoteDataSource {

    fun requestMovieInfo(query: String): Single<List<MovieEntity>>
}