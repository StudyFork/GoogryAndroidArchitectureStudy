package io.github.sooakim.domain.repository

import androidx.room.EmptyResultSetException
import io.github.sooakim.data.local.source.SAMovieLocalDataSource
import io.github.sooakim.data.remote.source.SAMovieRemoteDataSource
import io.github.sooakim.domain.mapper.SAMovieMapper
import io.github.sooakim.domain.model.SAMovieModel
import io.reactivex.Flowable

class SAMovieRepositoryImpl(
    private val movieLocalDataSource: SAMovieLocalDataSource,
    private val movieRemoteDataSource: SAMovieRemoteDataSource,
    private val movieMapper: SAMovieMapper
) : SAMovieRepository {
    override val latestMovieQuery: String
        get() = movieLocalDataSource.latestMovieQuery

    override fun getMovies(query: String): Flowable<List<SAMovieModel>> {
        movieLocalDataSource.latestMovieQuery = query
        return movieLocalDataSource.getMovies(query)
            .map { it.map(movieMapper::mapToModel) }
            .retryWhen { retryWhen ->
                retryWhen.flatMap { error ->
                    if (error is EmptyResultSetException) movieRemoteDataSource.getMovies(query)
                        .flatMapCompletable(movieLocalDataSource::saveMovies)
                        .toSingleDefault(Unit)
                        .toFlowable()
                    else Flowable.error(error)
                }
            }
            .onErrorReturn { listOf() }
    }
}