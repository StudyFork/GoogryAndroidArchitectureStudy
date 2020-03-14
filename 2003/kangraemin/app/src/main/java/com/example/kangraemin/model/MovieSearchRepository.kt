package com.example.kangraemin.model

import android.content.Context
import com.example.kangraemin.model.local.datamodel.Movie
import com.example.kangraemin.model.remote.datadao.RemoteMovieDataSource
import com.example.kangraemin.model.remote.datamodel.MovieDetail
import com.example.kangraemin.model.remote.datamodel.Movies
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class MovieSearchRepository(
    val remoteMovieDatasource: RemoteMovieDataSource
) {

    fun getMovieData(context: Context, query: String): Flowable<Movies> {
        return Flowable
            .defer {
                val db = AppDatabase.getInstance(context)

                val getLocalMovies = db.movieDao().getAll()
                    .subscribeOn(Schedulers.io())
                    .map { getMovieDataInRoom(it) }
                    .toFlowable()

                val getRemoteMovies = remoteMovieDatasource
                    .getMovies(
                        query = query
                    )
                    .subscribeOn(Schedulers.io())
                    .flatMap {
                        db.movieDao().deleteAll()
                            .andThen(Flowable.just(it))
                            .map { mappingMovieDataToLocal(it) }
                            .flatMapCompletable { db.movieDao().insertMovies(it) }
                            .andThen(Flowable.just(it))
                    }

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

    private fun getMovieDataInRoom(movies: List<Movie>): Movies {
        return Movies(
            items = ArrayList(
                movies
                    .map {
                        MovieDetail(
                            title = it.title,
                            image = it.image,
                            director = it.director,
                            actor = it.actor,
                            userRating = it.userRating,
                            pubDate = it.pubDate
                        )
                    })
        )
    }

    private fun mappingMovieDataToLocal(movies: Movies): List<Movie> {
        return movies.items.toList()
            .map {
                Movie(
                    title = it.title,
                    image = it.image,
                    director = it.director,
                    actor = it.actor,
                    userRating = it.userRating,
                    pubDate = it.pubDate
                )
            }
    }
}