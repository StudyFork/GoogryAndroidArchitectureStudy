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
        repository.getLatestMovieResult()
            .compose(singleIoMainThread())
            .subscribe({
                view.updateUi(it.keyword, it.movies)
            }, { e ->
                val message = e.message ?: return@subscribe
                Log.e("movie", message)
            })
            .addTo(disposables)
    }

    override fun search(keyword: String) {
        repository.getMovie(
            keyword = keyword
        )
            .flatMap {
                repository.refreshMovieSearchHistory(
                    keyword = keyword,
                    movies = it.movies
                )
            }
            .compose(singleIoMainThread())
            .subscribe({ movieRepo ->
                val movies = movieRepo.movies
                if (movies.isEmpty()) {
                } else {
                }
                view.updateResult(movies)
            }, { e ->
                handleError(e)
            })
            .addTo(disposables)
    }

}