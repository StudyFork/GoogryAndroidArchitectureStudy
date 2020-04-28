package com.example.data.source

import com.example.data.model.Movie
import com.example.data.source.local.LocalMovieDataSource
import com.example.data.source.remote.MovieRemoteDataSource
import io.reactivex.Flowable
import io.reactivex.Single

internal class MovieSearchRepositoryImpl(
    private val remoteMovieDataSource: MovieRemoteDataSource,
    private val localMovieDataSource: LocalMovieDataSource
) : MovieSearchRepository {

    override fun getMovieData(query: String): Flowable<List<Movie>> {
        return Flowable
            .defer {
                val getLocalMovies = localMovieDataSource.getAll()
                    .toFlowable()

                val getRemoteMovies = remoteMovieDataSource
                    .getMovies(
                        query = query
                    )
                    .flatMap {
                        localMovieDataSource
                            .deleteAll()
                            .andThen(Single.just(it))
                            .flatMapCompletable {
                                localMovieDataSource.insertMovies(it)
                            }
                            .andThen(Single.just(it))
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