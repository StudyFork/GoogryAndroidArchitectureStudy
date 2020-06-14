package io.github.jesterz91.data.repository

import io.github.jesterz91.data.mapper.MovieEntityMapper
import io.github.jesterz91.data.source.MovieLocalDataSource
import io.github.jesterz91.data.source.MovieRemoteDataSource
import io.github.jesterz91.domain.model.Movie
import io.github.jesterz91.domain.repository.MovieRepository
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class MovieRepositoryImpl(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override fun getMovieInfo(query: String): Flowable<List<Movie>> {
        return movieLocalDataSource.loadMovieInfo(query)
            .subscribeOn(Schedulers.io())
            .switchIfEmpty(Single.defer {
                movieRemoteDataSource
                    .requestMovieInfo(query)
                    .doOnSuccess { movieLocalDataSource.saveMovieInfo(it, query) }
            })
            .map { it.map(MovieEntityMapper::mapToDomain) }
            .toFlowable()
    }
}