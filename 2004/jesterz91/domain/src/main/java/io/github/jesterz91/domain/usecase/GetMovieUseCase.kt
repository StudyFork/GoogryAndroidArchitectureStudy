package io.github.jesterz91.domain.usecase

import io.github.jesterz91.domain.model.Movie
import io.github.jesterz91.domain.repository.MovieRepository
import io.reactivex.Flowable

internal class GetMovieUseCase(
    private val movieRepository: MovieRepository
) : UseCase<Flowable<List<Movie>>, String>() {

    override fun buildUseCase(params: String): Flowable<List<Movie>> {
        return movieRepository.getMovieInfo(params)
    }
}