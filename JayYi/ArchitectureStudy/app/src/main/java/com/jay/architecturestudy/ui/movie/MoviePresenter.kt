package com.jay.architecturestudy.ui.movie

import android.util.Log
import com.jay.architecturestudy.data.database.entity.MovieEntity
import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.ui.BaseSearchPresenter
import com.jay.architecturestudy.util.addTo
import com.jay.architecturestudy.util.then
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MoviePresenter(
    override val view: MovieContract.View,
    override val repository: NaverSearchRepositoryImpl
) : BaseSearchPresenter(view, repository), MovieContract.Presenter {

    override fun subscribe() {
        super.subscribe()
        loadData()
    }

    private fun loadData() {
        val lastKeyword = repository.getLatestMovieKeyword()
        if (lastKeyword.isBlank()) {
            view.updateUi(lastKeyword, emptyList())
        } else {
            repository.getLatestMovieResult()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
            .map {
                // 기존 결과 삭제
                clearSearchHistory { repository.clearMovieResult() }
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
                    // 최신 결과 저장
                    updateSearchHistory { repository.saveMovieResult(movieList) }
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
}