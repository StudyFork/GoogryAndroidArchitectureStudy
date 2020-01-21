package com.jay.architecturestudy.ui.movie

import androidx.databinding.ObservableField
import com.jay.architecturestudy.data.model.Movie
import com.jay.architecturestudy.data.repository.NaverSearchRepository
import com.jay.architecturestudy.ui.BaseViewModel
import com.jay.architecturestudy.ui.ViewType
import com.jay.architecturestudy.util.addTo
import com.jay.architecturestudy.util.singleIoMainThread

class MovieViewModel(
    override val repository: NaverSearchRepository
): BaseViewModel<Movie>(repository) {
    override val data: ObservableField<List<Movie>> = ObservableField()

    override fun init() {
        repository.getLatestMovieResult()
            .compose(singleIoMainThread())
            .subscribe({
                keyword.set(it.keyword)
                data.set(it.movies)
            }, { e ->
                val message = e.message ?: return@subscribe
                errorMsg.set(message)
            })
            .addTo(compositeDisposable)
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
                viewType.set(
                    if (movieRepo.movies.isEmpty()) {
                        ViewType.VIEW_SEARCH_NO_RESULT
                    } else {
                        ViewType.VIEW_SEARCH_SUCCESS
                    }
                )
                data.set(movieRepo.movies)
            }, { e ->
                val message = e.message ?: return@subscribe
                errorMsg.set(message)
            })
            .addTo(compositeDisposable)
    }
}