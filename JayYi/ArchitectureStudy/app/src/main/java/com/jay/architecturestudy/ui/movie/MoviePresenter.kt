package com.jay.architecturestudy.ui.movie

import android.util.Log
import com.jay.architecturestudy.data.database.entity.MovieEntity
import com.jay.architecturestudy.data.model.Movie
import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.ui.BaseSearchPresenter
import com.jay.architecturestudy.util.addTo
import com.jay.architecturestudy.util.singleIoMainThread
import com.jay.architecturestudy.util.then
import io.reactivex.Completable
import io.reactivex.Single

class MoviePresenter(
    override val view: MovieContract.View,
    override val repository: NaverSearchRepositoryImpl
) : BaseSearchPresenter(view, repository), MovieContract.Presenter {

    override fun subscribe() {
        val lastKeyword = repository.getLatestMovieKeyword()
        lastKeyword.isNotBlank().then {
            repository.getLatestMovieResult()
                .compose(singleIoMainThread())
                .subscribe({
                    view.updateUi(lastKeyword, it)
                }, { e ->
                    val message = e.message ?: return@subscribe
                    Log.e("movie", message)
                })
                .addTo(disposables)
        }

    }

    override fun search(keyword: String) {
        repository.getMovie(
            keyword = keyword
        )
            .flatMap {
                if (it.movies.isEmpty()) {
                    updateMovieSearchHistory(
                        it.movies,
                        fun1 = { repository.clearMovieResult() },
                        fun2 = { repository.saveMovieKeyword(keyword) }
                    )
                } else {
                    val movieList = ensureMovieEntityList(it.movies)
                    updateMovieSearchHistory(
                        it.movies,
                        fun1 = { repository.clearMovieResult() },
                        fun2 = { repository.saveMovieKeyword(keyword) },
                        fun3 = { repository.saveMovieResult(movieList) })
                }
            }
            .compose(singleIoMainThread())
            .subscribe({ movies ->
                if (movies.isEmpty()) {
                    view.hideResultListView()
                    view.showEmptyResultView()
                } else {
                    view.hideEmptyResultView()
                    view.showResultListView()
                }
                view.updateResult(movies)
            }, { e ->
                handleError(e)
            })
            .addTo(disposables)
    }

    private fun ensureMovieEntityList(movies: List<Movie>): List<MovieEntity> =
        arrayListOf<MovieEntity>().apply {
            movies.mapTo(this) { movie ->
                MovieEntity(
                    title = movie.title,
                    link = movie.link,
                    image = movie.image,
                    subtitle = movie.subtitle,
                    director = movie.director,
                    actor = movie.actor,
                    pubDate = movie.pubDate,
                    userRating = movie.userRating
                )
            }
        }

    private fun updateMovieSearchHistory(
        movies: List<Movie>,
        fun1: () -> Unit,
        fun2: () -> Unit,
        fun3: (() -> Unit)? = null
    ): Single<List<Movie>> {
        val firstCall = Completable.fromCallable(fun1)
        val secondCall = Completable.fromCallable(fun2)
        return fun3?.let { call ->
            val thirdCall = Completable.fromCallable(call)
            firstCall
                .andThen(secondCall)
                .andThen(thirdCall)
                .toSingle { movies }
        } ?: run {
            firstCall
                .andThen(secondCall)
                .toSingle { movies }
        }
    }
}