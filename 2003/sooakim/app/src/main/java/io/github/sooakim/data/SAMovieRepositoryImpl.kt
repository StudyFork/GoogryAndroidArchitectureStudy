package io.github.sooakim.data

import io.github.sooakim.data.local.source.SAMovieLocalDataSource
import io.github.sooakim.data.mapper.SAMovieDataMapper
import io.github.sooakim.data.model.SAMovieData
import io.github.sooakim.data.remote.source.SAMovieRemoteDataSource
import io.github.sooakim.domain.model.SAMovieModel
import io.github.sooakim.domain.repository.SAMovieRepository
import io.reactivex.Flowable
import io.reactivex.Single

class SAMovieRepositoryImpl(
    private val movieLocalDataSource: SAMovieLocalDataSource,
    private val movieRemoteDataSource: SAMovieRemoteDataSource,
    private val movieMapper: SAMovieDataMapper
) : SAMovieRepository {
    override val latestMovieQuery: String
        get() = movieLocalDataSource.latestMovieQuery

    override fun getMovies(query: String): Flowable<List<SAMovieModel>> {
        movieLocalDataSource.latestMovieQuery = query
        return movieLocalDataSource.getMovies()
            .onErrorReturn { listOf() }
            .flatMapPublisher { cachedMovies ->
                if (cachedMovies.isEmpty()) {
                    getRemoteMovies(query)
                        .map { it.map(movieMapper::mapToModel) }
                        .toFlowable()
                        .onErrorReturn { listOf() }
                } else {
                    val local = Single.just(cachedMovies)
                        .map { it.map(movieMapper::mapToModel) }
                    val remote = getRemoteMovies(query)
                        .map { it.map(movieMapper::mapToModel) }
                        .onErrorResumeNext { local }
                    Single.concat(local, remote)
                }
            }
    }

    private fun getRemoteMovies(query: String): Single<List<SAMovieData>> {
        return movieRemoteDataSource.getMovies(query)
            .flatMap { remoteMovies ->
                movieLocalDataSource.saveMovies(remoteMovies)
                    .andThen(Single.just(remoteMovies))
            }
    }
}