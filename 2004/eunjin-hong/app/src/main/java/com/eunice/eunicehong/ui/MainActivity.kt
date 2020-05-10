package com.eunice.eunicehong.ui

import android.app.AlertDialog
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.eunice.eunicehong.R
import com.eunice.eunicehong.data.model.MovieList
import com.eunice.eunicehong.data.source.MovieDataSource
import com.eunice.eunicehong.databinding.ActivityMainBinding
import com.eunice.eunicehong.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var searchView: SearchView

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = MainViewModel(this@MainActivity)

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
            mainViewModel.search(query, object : MovieDataSource.LoadMoviesCallback {
                override fun onSuccess(movieList: MovieList) {
                    mainViewModel.showSearchResult(query, movieList)
                }

                override fun onFailure(e: Throwable) {
                    Log.d(this.toString(), e.toString())
                }
            })
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

    fun showRemoveHistoryConfirmDialog(item: MenuItem) {
        AlertDialog.Builder(this)
            .setTitle(R.string.app_name)
            .setMessage(getString(R.string.delete_history_confirmation))
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setPositiveButton(
                android.R.string.yes
            ) { _, _ ->
                mainViewModel.removeHistory()
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.complete_delete_history),
                    Toast.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton(android.R.string.no, null).show()
    }


    companion object {
        private const val TAG = "MAIN_ACTIVITY"
    }
}
