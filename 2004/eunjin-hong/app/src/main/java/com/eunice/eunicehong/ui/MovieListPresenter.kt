package com.eunice.eunicehong.ui

import android.provider.SearchRecentSuggestions
import com.eunice.eunicehong.R
import com.eunice.eunicehong.data.source.MovieDataSource
import com.eunice.eunicehong.data.source.MovieRepository
import com.eunice.eunicehong.provider.SuggestionProvider
import com.eunice.eunicehong.util.ToastMessage

class MovieListPresenter(
    private val view: MovieContract.View,
    private val cache: MovieCache
) : MovieContract.Presenter {

    override fun search(query: String, callback: MovieDataSource.LoadMoviesCallback) {
        SearchRecentSuggestions(
            view.movieContext,
            SuggestionProvider.AUTHORITY,
            SuggestionProvider.MODE
        ).saveRecentQuery(query, null)

        try {
            val list = cache.getMovieList(query)
            if (list.items.isEmpty()) {
                MovieRepository.getMovieList(query, callback)
            } else {
                callback.onSuccess(list)
                ToastMessage.show(view.movieContext, "☝️ 저장된 결과를 가져왔습니다.")
            }
        } catch (e: Throwable) {
            MovieRepository.getMovieList(query, callback)
        }
    }

    override fun removeHistory() {
        cache.removeMovieHistory()
        SearchRecentSuggestions(
            view.movieContext,
            SuggestionProvider.AUTHORITY,
            SuggestionProvider.MODE
        ).clearHistory()
    }

    override fun onOptionMenuSelected(id: Int): Boolean = when (id) {
        R.id.remove_history -> {
            view.showRemoveHistoryConfirmDialog()
            true
        }
        else -> {
            false
        }
    }
}