package com.example.kangraemin.model

import android.content.Context
import com.example.kangraemin.model.local.datamodel.Movie
import com.example.kangraemin.model.remote.datadao.MovieImpl
import com.example.kangraemin.model.remote.datamodel.MovieDetail
import com.example.kangraemin.model.remote.datamodel.Movies
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class MovieSearchRepository {


    fun getMovieData(context: Context, query: String): Flowable<Movies> {
        return Flowable
            .create({ emitter ->
                val db = AppDatabase.getInstance(context)

                val getLocalMovies = db.movieDao().getAll()
                    .subscribeOn(Schedulers.io())
                    .map { getMovieDataInRoom(it) }
                    .toFlowable()

                val getRemoteMovies = MovieImpl.getInstance()
                    .getMovies(
                        query = query
                    )
                    .subscribeOn(Schedulers.io())
                    .map {
                        db.movieDao().apply {
                            deleteAll()
                                .andThen(
                                    insertMovies(mappingMovieDataToLocal(it))
                                ).subscribe({}, { t -> t.printStackTrace() })
                        }
                        return@map it
                    }

                Flowable
                    .defer {
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
                    .subscribe({
                        emitter.onNext(it)
                    }, {
                        emitter.onError(it)
                    })
            }, BackpressureStrategy.BUFFER)
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