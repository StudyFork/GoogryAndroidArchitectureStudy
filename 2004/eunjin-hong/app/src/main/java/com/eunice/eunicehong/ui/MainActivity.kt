package com.eunice.eunicehong.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.eunice.eunicehong.R
import com.eunice.eunicehong.data.repository.MovieRepository
import com.eunice.eunicehong.provider.SuggestionProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val movieListAdapter = MovieAdapter()

    private var queryText: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setMovieList()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        if (!queryText.isNullOrBlank()) {
            SearchRecentSuggestions(
                this,
                SuggestionProvider.AUTHORITY,
                SuggestionProvider.MODE
            ).saveRecentQuery(queryText, null)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search_bar).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            isIconifiedByDefault = false
            isSubmitButtonEnabled = true
            isQueryRefinementEnabled = true

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (!query.isNullOrBlank()) {
                        queryText = query
                        searchMovieList(query)
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    queryText = newText
                    return false
                }
            })
        }
        return true
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
