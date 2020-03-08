package io.github.sooakim.data.remote.source

import io.github.sooakim.data.model.SAMovieEntity
import io.reactivex.Single

interface SAMovieRemoteDataSource : SARemoteDataSource {
    fun getMovies(query: String): Single<List<SAMovieEntity>>
}