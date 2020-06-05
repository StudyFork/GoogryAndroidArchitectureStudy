package io.github.jesterz91.study.domain.di

import io.github.jesterz91.study.data.local.model.MovieLocal
import io.github.jesterz91.study.data.remote.model.MovieRemote
import io.github.jesterz91.study.domain.entity.Movie
import io.github.jesterz91.study.domain.mapper.Mapper
import io.github.jesterz91.study.domain.mapper.MovieLocalMapper
import io.github.jesterz91.study.domain.mapper.MovieRemoteMapper
import io.github.jesterz91.study.domain.repository.MovieRepository
import io.github.jesterz91.study.domain.repository.MovieRepositoryImpl
import io.github.jesterz91.study.domain.usecase.GetMovieUseCase
import io.github.jesterz91.study.domain.usecase.UseCase
import io.reactivex.Flowable
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {

    single<UseCase<Flowable<List<Movie>>, String>> { GetMovieUseCase(get()) }

    single<MovieRepository> {
        MovieRepositoryImpl(
            get(),
            get(named("local")),
            get(),
            get(named("remote"))
        )
    }

    single<Mapper<List<Movie>, List<MovieLocal>>>(named("local")) { MovieLocalMapper() }

    single<Mapper<List<Movie>, List<MovieRemote>>>(named("remote")) { MovieRemoteMapper() }
}