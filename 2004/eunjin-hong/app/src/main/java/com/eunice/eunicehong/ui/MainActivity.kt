package com.eunice.eunicehong.ui

import android.app.AlertDialog
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eunice.eunicehong.R
import com.eunice.eunicehong.data.repository.MovieRepository
import com.eunice.eunicehong.provider.SuggestionProvider
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val movieListAdapter = MovieAdapter()

    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setMovieList()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        intent?.getStringExtra(SearchManager.QUERY)
            ?.also { query ->
                SearchRecentSuggestions(
                    this,
                    SuggestionProvider.AUTHORITY,
                    SuggestionProvider.MODE
                ).saveRecentQuery(query, null)
                searchView.setQuery(query, false)
                searchView.clearFocus()
                searchMovieList(query)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.search_bar).actionView as SearchView
        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            isIconifiedByDefault = false
            isSubmitButtonEnabled = true
            isQueryRefinementEnabled = true
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.remove_history -> {
            showRemoveHistoryConfirmDialog()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun showRemoveHistoryConfirmDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.app_name)
            .setMessage(getString(R.string.delete_history_confirmation))
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setPositiveButton(
                android.R.string.yes
            ) { _, _ ->
                clearHistory()
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.complete_delete_history),
                    Toast.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton(android.R.string.no, null).show()
    }

    private fun clearHistory() {
        SearchRecentSuggestions(
            this,
            SuggestionProvider.AUTHORITY,
            SuggestionProvider.MODE
        ).clearHistory()
    }

    private fun searchMovieList(query: String) {
        MovieRepository.getMovieList(
            query = query,
            onSuccess = { movies ->
                zero_item_message.visibility =
                    if (movies.isNullOrEmpty()) View.VISIBLE else View.GONE
                movieListAdapter.setMovieList(movies)
            },
            onFailure = { e: Throwable ->
                Log.d(TAG, e.toString())
            })
    }

    private fun setMovieList() {
        move_list.adapter = movieListAdapter
    }

    companion object {
        private const val TAG = "MAIN_ACTIVITY"
    }

}
