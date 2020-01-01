package com.jay.architecturestudy.ui.movie

import com.jay.architecturestudy.data.database.entity.MovieEntity
import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.ui.BaseSearchPresenter
import com.jay.architecturestudy.util.addTo
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MoviePresenter(
    override val view: MovieContract.View,
    override val repository: NaverSearchRepositoryImpl
) : BaseSearchPresenter(view, repository), MovieContract.Presenter {

    override fun search(keyword: String) {
        repository.getMovie(
            keyword = keyword
        )
            .map {
                // 기존 결과 삭제
                updateMovieResult { repository.clearMovieResult() }
                it.movies.isNotEmpty().then {
                    val movieList = arrayListOf<MovieEntity>()
                    it.movies.mapTo(movieList) { movie ->
                        MovieEntity(
                            title = movie.title,
                            link =  movie.link,
                            image = movie.image,
                            subtitle = movie.subtitle,
                            director = movie.director,
                            actor = movie.actor,
                            pubDate = movie.pubDate,
                            userRating = movie.userRating
                        )
                    }
                    updateMovieResult {
                        // 최신 결과 저장
                        repository.saveMovieResult(movieList)
                    }
                }
                repository.saveMovieKeyword(keyword)
                it.movies
            }
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

    private fun updateMovieResult(func: () -> Unit) {
        Completable.fromCallable(func)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}