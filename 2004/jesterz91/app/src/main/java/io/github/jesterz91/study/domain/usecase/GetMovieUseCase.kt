package io.github.jesterz91.study.domain.usecase

import io.github.jesterz91.study.domain.entity.Movie
import io.github.jesterz91.study.domain.repository.MovieRepository
import io.reactivex.Flowable

class GetMovieUseCase(
    private val movieRepository: MovieRepository
) : UseCase<Flowable<List<Movie>>, String>() {

    override fun buildUseCase(params: String): Flowable<List<Movie>> {
        return movieRepository.getMovieInfo(params)
    }
}