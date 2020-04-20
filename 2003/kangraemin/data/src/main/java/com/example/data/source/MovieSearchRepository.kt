package com.example.data.source

import com.example.data.mapper.MovieMapper
import com.example.data.model.Movie
import com.example.local.source.LocalMovieDataSource
import com.example.remote.source.MovieRemoteDataSource
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class MovieSearchRepository(
    private val remoteMovieDataSource: MovieRemoteDataSource,
    private val localMovieDataSource: LocalMovieDataSource
) {

    fun getMovieData(query: String): Flowable<List<Movie>> {
        return Flowable
            .defer {
                val getLocalMovies = localMovieDataSource.getAll()
                    .subscribeOn(Schedulers.io())
                    .map { MovieMapper.localMovieToDataMovie(it) }
                    .toFlowable()

                val getRemoteMovies = remoteMovieDataSource
                    .getMovies(
                        query = query
                    )
                    .subscribeOn(Schedulers.io())
                    .flatMap {
                        localMovieDataSource
                            .deleteAll()
                            .subscribeOn(Schedulers.io())
                            .andThen(Single.just(it))
                            .map { MovieMapper.remoteMovieToLocalMovie(it) }
                            .flatMapCompletable {
                                localMovieDataSource.insertMovies(it)
                                    .subscribeOn(Schedulers.io())
                            }
                            .andThen(Single.just(MovieMapper.remoteMovieToDataMovie(it)))
                    }
                    .toFlowable()

                if (query.isNotEmpty()) {
                    Flowable
                        .concat(
                            getLocalMovies,
                            getRemoteMovies
                        )
                } else {
                    getLocalMovies
                }
            }
    }
}