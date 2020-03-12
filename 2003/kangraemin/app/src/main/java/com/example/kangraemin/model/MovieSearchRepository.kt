package com.example.kangraemin.model

import android.content.Context
import com.example.kangraemin.model.local.datamodel.Movie
import com.example.kangraemin.model.remote.datadao.MovieImpl
import com.example.kangraemin.model.remote.datamodel.MovieDetail
import com.example.kangraemin.model.remote.datamodel.Movies
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class MovieSearchRepository {

    private val movieData = BehaviorSubject.create<Movies>()

    fun getMovieData(context: Context, query: String): BehaviorSubject<Movies> {

        val db = AppDatabase.getInstance(context)

        db.movieDao().getAll()
            .subscribeOn(Schedulers.io())
            .map {
                val localDataItemsMovieSearch = ArrayList<MovieDetail>()
                for (movie in it) {
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
                localDataItemsMovieSearch
            }
            .observeOn(AndroidSchedulers.mainThread())
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
                .map { itemsMovieSearch ->
                    db.movieDao().deleteAll()
                    for (searchedMovie in itemsMovieSearch.items) {
                        val movie = Movie(
                            title = searchedMovie.title,
                            image = searchedMovie.image,
                            director = searchedMovie.director,
                            actor = searchedMovie.actor,
                            userRating = searchedMovie.userRating,
                            pubDate = searchedMovie.pubDate
                        )
                        db.movieDao().insert(movie)
                    }
                    itemsMovieSearch
                }
                .subscribe({ itemsMovieSearch ->
                    movieData.onNext(itemsMovieSearch)
                }, { movieData.onError(it) })
        }
        return movieData
    }
}