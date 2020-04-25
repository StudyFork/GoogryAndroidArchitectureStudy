package io.github.jesterz91.study.domain.repository

import io.github.jesterz91.study.data.local.model.MovieLocal
import io.github.jesterz91.study.data.local.source.MovieLocalDataSource
import io.github.jesterz91.study.data.remote.model.MovieRemote
import io.github.jesterz91.study.data.remote.source.MovieRemoteDataSource
import io.github.jesterz91.study.domain.entity.Movie
import io.github.jesterz91.study.domain.mapper.Mapper
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class MovieRepositoryImpl(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieLocalMapper: Mapper<List<Movie>, List<MovieLocal>>,
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieRemoteMapper: Mapper<List<Movie>, List<MovieRemote>>
) : MovieRepository {

    override fun getMovieInfo(query: String): Flowable<List<Movie>> {
        return movieLocalDataSource.loadMovieInfo(query)
            .subscribeOn(Schedulers.io())
            .map(movieLocalMapper::toDomain)
            .switchIfEmpty(Single.defer {
                movieRemoteDataSource
                    .requestMovieInfo(query)
                    .map(movieRemoteMapper::toDomain)
                    .doOnSuccess { movies ->
                        val localMovies = movieLocalMapper.toData(movies)
                        localMovies.forEach { it.searchQuery = query }
                        movieLocalDataSource.saveMovieInfo(localMovies)
                    }
            }).toFlowable()
    }
}