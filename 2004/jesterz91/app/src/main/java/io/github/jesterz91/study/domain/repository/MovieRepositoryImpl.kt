package io.github.jesterz91.study.domain.repository

import io.github.jesterz91.study.data.local.model.MovieLocal
import io.github.jesterz91.study.data.local.source.MovieLocalDataSource
import io.github.jesterz91.study.data.remote.model.MovieRemote
import io.github.jesterz91.study.data.remote.source.MovieRemoteDataSource
import io.github.jesterz91.study.domain.entity.Movie
import io.github.jesterz91.study.domain.mapper.Mapper
import io.reactivex.Single

class MovieRepositoryImpl(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieLocalMapper: Mapper<List<Movie>, List<MovieLocal>>,
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieRemoteMapper: Mapper<List<Movie>, List<MovieRemote>>
) : MovieRepository {

    override fun getMovie(query: String): Single<List<Movie>> {
        // 현재는 remote 에서만 데이터를 가져옴.
        return movieRemoteDataSource.requestMovie(query)
            .map(movieRemoteMapper::toDomain)
    }
}