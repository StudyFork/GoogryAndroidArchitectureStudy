package io.github.jesterz91.study.data.remote.source

import io.github.jesterz91.study.data.remote.model.MovieRemote
import io.reactivex.Single

interface MovieRemoteDataSource {

    fun requestMovie(query: String): Single<List<MovieRemote>>
}