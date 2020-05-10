package com.eunice.eunicehong.viewmodel

import android.app.AlertDialog
import android.content.Context
import android.provider.SearchRecentSuggestions
import android.widget.Toast
import androidx.databinding.ObservableField
import com.eunice.eunicehong.R
import com.eunice.eunicehong.data.model.MovieList
import com.eunice.eunicehong.data.source.MovieDataSource
import com.eunice.eunicehong.data.source.MovieRepository
import com.eunice.eunicehong.provider.SuggestionProvider
import com.eunice.eunicehong.ui.MovieAdapter
import com.eunice.eunicehong.ui.MovieCache
import com.eunice.eunicehong.ui.MoviePreferences
import com.eunice.eunicehong.util.ToastMessage
import com.google.gson.JsonSyntaxException

class MainViewModel(private val context: Context) {

    private val cache = object : MovieCache {
        val preferences = MoviePreferences.getInstance(context)

        @Throws(IllegalStateException::class, JsonSyntaxException::class)
        override fun getMovieList(
            query: String
        ): MovieList = preferences.getHistory(query)

        override fun saveMovieList(query: String, movieList: MovieList) =
            preferences.saveHistory(query, movieList)


        override fun removeMovieHistory() {
            preferences.removeAllSearchHistory()
        }
    }

    val movieListAdapter = MovieAdapter()

    val movieListState: ObservableField<MovieListState> =
        ObservableField(MovieListState.EMPTY_QUERY)

    fun search(query: String, callback: MovieDataSource.LoadMoviesCallback) {
        SearchRecentSuggestions(
            context,
            SuggestionProvider.AUTHORITY,
            SuggestionProvider.MODE
        ).saveRecentQuery(query, null)

        try {
            val list = cache.getMovieList(query)
            if (list.items.isEmpty()) {
                MovieRepository.getMovieList(query, callback)
            } else {
                callback.onSuccess(list)
                ToastMessage.show(context, "☝️ 저장된 결과를 가져왔습니다.")
            }
        } catch (e: Throwable) {
            MovieRepository.getMovieList(query, callback)
        }
    }

    fun showRemoveHistoryConfirmDialog() {
        AlertDialog.Builder(context)
            .setTitle(R.string.app_name)
            .setMessage(context.getString(R.string.delete_history_confirmation))
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setPositiveButton(
                android.R.string.yes
            ) { _, _ ->
                removeHistory()
                Toast.makeText(
                    context,
                    context.getString(R.string.complete_delete_history),
                    Toast.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton(android.R.string.no, null).show()
    }

    fun removeHistory() {
        cache.removeMovieHistory()
        SearchRecentSuggestions(
            context,
            SuggestionProvider.AUTHORITY,
            SuggestionProvider.MODE
        ).clearHistory()
    }


    fun showSearchResult(query: String, movies: MovieList) {
        if (movies.items.isEmpty()) {
            movieListState.set(MovieListState.NO_MATCHING_RESULT)
        } else {
            movieListAdapter.setMovieList(movies.items)
            movieListState.set(MovieListState.SHOW_RESULT)
        }
        cache.saveMovieList(query, movies)
    }

    enum class MovieListState {
        NETWORK_ERROR,
        NO_MATCHING_RESULT,
        SHOW_RESULT,
        LOADING,
        EMPTY_QUERY
    }
}