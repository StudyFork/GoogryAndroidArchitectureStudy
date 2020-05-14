package com.eunice.eunicehong.ui

import android.app.AlertDialog
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.eunice.eunicehong.R
import com.eunice.eunicehong.data.model.MovieList
import com.eunice.eunicehong.databinding.ActivityMainBinding
import com.eunice.eunicehong.provider.SuggestionProvider
import com.eunice.eunicehong.viewmodel.MainViewModel
import com.google.gson.JsonSyntaxException

class MainActivity : AppCompatActivity() {

    private val cache = object : MovieCache {
        val preferences = MoviePreferences.getInstance(this@MainActivity)

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

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = MainViewModel(cache)

        DataBindingUtil.setContentView<ActivityMainBinding>(
            this@MainActivity,
            R.layout.activity_main
        ).apply {
            viewModel = mainViewModel
            lifecycleOwner = this@MainActivity
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val query = intent?.getStringExtra(SearchManager.QUERY)
        if (!query.isNullOrBlank()) {
            SearchRecentSuggestions(
                this@MainActivity,
                SuggestionProvider.AUTHORITY,
                SuggestionProvider.MODE
            ).saveRecentQuery(query, null)

            mainViewModel.search(query)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search_bar).actionView as SearchView
        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            isIconifiedByDefault = false
            isSubmitButtonEnabled = true
            isQueryRefinementEnabled = true
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return if (newText.isNullOrBlank()) {
                        mainViewModel.movieListState.set(MainViewModel.MovieListState.EMPTY_QUERY)
                        true
                    } else {
                        false
                    }
                }

            })
        }
        return true
    }

    fun showRemoveHistoryConfirmDialog(item: MenuItem) {
        if (item.itemId == R.id.remove_history) {
            AlertDialog.Builder(this@MainActivity)
                .setTitle(R.string.app_name)
                .setMessage(getString(R.string.delete_history_confirmation))
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(
                    android.R.string.yes
                ) { _, _ ->
                    mainViewModel.removeHistory()

                    SearchRecentSuggestions(
                        this@MainActivity,
                        SuggestionProvider.AUTHORITY,
                        SuggestionProvider.MODE
                    ).clearHistory()

                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.complete_delete_history),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .setNegativeButton(android.R.string.no, null).show()
        }
    }


    companion object {
        private const val TAG = "MAIN_ACTIVITY"
    }
}
