package com.eunice.eunicehong.ui

import android.content.Intent
import android.net.Uri
import android.provider.SearchRecentSuggestions
import com.eunice.eunicehong.data.repository.MovieRepository
import com.eunice.eunicehong.data.source.MovieDataSource
import com.eunice.eunicehong.provider.SuggestionProvider
import com.eunice.eunicehong.util.ToastMessage

class MoviePresenter(
    private val view: MovieContract.View,
    private val cache: MovieCache
) : MovieContract.Presenter {

    override fun showDetail(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        view.movieContext.startActivity(intent)
    }

    override fun search(query: String, callback: MovieDataSource.LoadMoviesCallback) {
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
}