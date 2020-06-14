package io.github.jesterz91.domain.di

import io.github.jesterz91.domain.model.Movie
import io.github.jesterz91.domain.usecase.GetMovieUseCase
import io.github.jesterz91.domain.usecase.UseCase
import io.reactivex.Flowable
import org.koin.dsl.module

val domainModule = module {

    single<UseCase<Flowable<List<Movie>>, String>> { GetMovieUseCase(get()) }
}