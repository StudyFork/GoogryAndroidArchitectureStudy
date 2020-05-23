package com.eunice.eunicehong.ui

import android.app.AlertDialog
import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.eunice.eunicehong.R
import com.eunice.eunicehong.data.source.MovieRepository
import com.eunice.eunicehong.data.source.local.MovieLocalDataSource
import com.eunice.eunicehong.data.source.remote.MovieRemoteDataSource
import com.eunice.eunicehong.databinding.ActivityMainBinding
import com.eunice.eunicehong.provider.SuggestionProvider
import com.eunice.eunicehong.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    val movieListAdapter = MovieAdapter()

    private lateinit var sharedPreferences: MoviePreferences
    private lateinit var searchRecentSuggestions: SearchRecentSuggestions

    private lateinit var localDataSource: MovieLocalDataSource
    private lateinit var remoteDataSource: MovieRemoteDataSource
    private lateinit var movieRepository: MovieRepository

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = MoviePreferences(this@MainActivity)
        searchRecentSuggestions = SearchRecentSuggestions(
            this@MainActivity,
            SuggestionProvider.AUTHORITY,
            SuggestionProvider.MODE
        )

        localDataSource = MovieLocalDataSource(sharedPreferences, searchRecentSuggestions)
        remoteDataSource = MovieRemoteDataSource()
        movieRepository = MovieRepository(remoteDataSource, localDataSource)
        mainViewModel = MainViewModel(movieRepository)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this@MainActivity,
            R.layout.activity_main
        ).apply {
            activity = this@MainActivity
            viewModel = mainViewModel
            componentName = this@MainActivity.componentName
            lifecycleOwner = this@MainActivity
        }

        setSupportActionBar(binding.toolbar)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val query = intent?.getStringExtra(SearchManager.QUERY)
        mainViewModel.search(query)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
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

                    mainViewModel.deleteAllSearchRecentSuggestions()

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
