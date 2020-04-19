package io.github.sooakim.data

import io.github.sooakim.data.local.SAMovieLocalDataSource
import io.github.sooakim.data.mapper.SAMovieDataMapper
import io.github.sooakim.data.model.SAMovieData
import io.github.sooakim.data.remote.SAMovieRemoteDataSource
import io.github.sooakim.domain.model.SAMovieModel
import io.github.sooakim.domain.repository.SAMovieRepository
import io.reactivex.Flowable
import io.reactivex.Single

internal class SAMovieRepositoryImpl(
    private val movieLocalDataSource: SAMovieLocalDataSource,
    private val movieRemoteDataSource: SAMovieRemoteDataSource
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
                        .map { it.map(SAMovieDataMapper::mapToModel) }
                        .toFlowable()
                        .onErrorReturn { listOf() }
                } else {
                    val local = Single.just(cachedMovies)
                        .map { it.map(SAMovieDataMapper::mapToModel) }
                    val remote = getRemoteMovies(query)
                        .map { it.map(SAMovieDataMapper::mapToModel) }
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