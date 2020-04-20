package com.eunice.eunicehong.ui

import android.content.Intent
import android.net.Uri
import android.provider.SearchRecentSuggestions
import android.util.Log
import com.eunice.eunicehong.data.repository.MovieRepository
import com.eunice.eunicehong.provider.SuggestionProvider

class MoviePresenter(private val view: MovieContract.View) : MovieContract.Presenter {
    override fun showDetail(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        view.movieContext.startActivity(intent)
    }

    override fun search(query: String) {
        MovieRepository.getMovieList(
            context = view.movieContext,
            query = query,
            success = { movies ->
                view.showSearchResult(movies)
            },
            failure = { e: Throwable ->
                Log.d(view.toString(), e.toString())
            })
    }

    override fun removeHistory() {
        MovieRepository.removeAllHistory(view.movieContext)
        SearchRecentSuggestions(
            view.movieContext,
            SuggestionProvider.AUTHORITY,
            SuggestionProvider.MODE
        ).clearHistory()
    }

    private fun getString(resId: Int) = view.movieContext.getString(resId)
}