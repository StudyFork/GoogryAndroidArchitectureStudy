package io.github.jesterz91.study.domain.usecase

import io.github.jesterz91.study.domain.entity.Movie
import io.github.jesterz91.study.domain.repository.MovieRepository
import io.reactivex.Single

class GetMovieUseCase(
    private val movieRepository: MovieRepository
) : UseCase<Single<List<Movie>>, String>() {

    override fun buildUseCase(params: String): Single<List<Movie>> {
        return movieRepository.getMovie(params)
    }
}