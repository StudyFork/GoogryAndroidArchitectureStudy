package com.example.data.source

import com.example.data.model.Movie
import com.example.data.source.local.LocalMovieDataSource
import com.example.data.source.remote.MovieRemoteDataSource
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

internal class MovieSearchRepositoryImpl(
    private val remoteMovieDataSource: MovieRemoteDataSource,
    private val localMovieDataSource: LocalMovieDataSource
) : MovieSearchRepository {

    override fun getMovieData(query: String): Flowable<List<Movie>> {
        return Flowable
            .defer {
                val getLocalMovies = localMovieDataSource.getAll()
                    .subscribeOn(Schedulers.io())
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
                            .flatMapCompletable {
                                localMovieDataSource.insertMovies(it)
                                    .subscribeOn(Schedulers.io())
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