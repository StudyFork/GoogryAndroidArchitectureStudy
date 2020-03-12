package com.example.kangraemin.model

import android.content.Context
import com.example.kangraemin.model.local.datamodel.Movie
import com.example.kangraemin.model.remote.datadao.MovieImpl
import com.example.kangraemin.model.remote.datamodel.MovieDetail
import com.example.kangraemin.model.remote.datamodel.Movies
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class MovieSearchRepository {

    private val movieData = BehaviorSubject.create<Movies>()

    fun getMovieData(context: Context, query: String): BehaviorSubject<Movies> {

        val db = AppDatabase.getInstance(context)

        db.movieDao().getAll()
            .subscribeOn(Schedulers.io())
            .map { getMovieDataInRoom(it) }
            .subscribe({ localDataItemsMovieSearch ->
                if (localDataItemsMovieSearch.isNotEmpty()) {
                    movieData.onNext(Movies(items = localDataItemsMovieSearch))
                }
            }, { movieData.onError(it) })

        if (query.isNotEmpty()) {
            MovieImpl()
                .getSearchItems(
                    display = "10",
                    start = "1",
                    query = query
                )
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    db.movieDao().apply {
                        deleteAll()
                            .andThen(insertMovies(mappingMovieDataToLocal(it)))
                    }
                }
                .subscribe({ itemsMovieSearch ->
                    movieData.onNext(itemsMovieSearch)
                }, { movieData.onError(it) })
        }
        return movieData
    }

    fun getMovieDataInRoom(movies: List<Movie>): ArrayList<MovieDetail> {
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
        return localDataItemsMovieSearch
    }

    fun mappingMovieDataToLocal(movies: Movies): List<Movie> {
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