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

                val getRemoteMovies = MovieImpl()
                    .getSearchItems(
                        display = "10",
                        start = "1",
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
        val localDataItemsMovieSearch = ArrayList<MovieDetail>()
        for (movie in movies) {
            val localMovieData = MovieDetail(
                title = movie.title,
                image = movie.image,
                director = movie.director,
                actor = movie.actor,
                userRating = movie.userRating,
                pubDate = movie.pubDate
            )
            localDataItemsMovieSearch.add(localMovieData)
        }
        return Movies(items = localDataItemsMovieSearch)
    }

    private fun mappingMovieDataToLocal(movies: Movies): List<Movie> {
        val localMovies = mutableListOf<Movie>()
        for (searchedMovie in movies.items) {
            val movie = Movie(
                title = searchedMovie.title,
                image = searchedMovie.image,
                director = searchedMovie.director,
                actor = searchedMovie.actor,
                userRating = searchedMovie.userRating,
                pubDate = searchedMovie.pubDate
            )
            localMovies.add(movie)
        }
        return localMovies.toList()
    }
}