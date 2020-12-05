package com.jay.aas.ui.movie

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jay.aas.R
import com.jay.aas.api.RetrofitHelper
import com.jay.aas.base.BaseActivity
import com.jay.aas.data.MovieLocalDataSourceImpl
import com.jay.aas.data.MovieRemoteDataSourceImpl
import com.jay.aas.data.MovieRepositoryImpl
import com.jay.aas.databinding.ActivityMovieBinding
import com.jay.aas.room.AppDatabase
import com.jay.aas.ui.history.SearchHistoryActivity
import com.jay.aas.util.toast

class MovieActivity : BaseActivity<ActivityMovieBinding>(
    R.layout.activity_movie
) {

    private val TAG = this::class.java.simpleName

    private val viewModel by viewModels<MovieViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val appDatabase = AppDatabase.getInstance(this@MovieActivity)
                val movieRepository = MovieRepositoryImpl(
                    MovieRemoteDataSourceImpl(RetrofitHelper.movieService),
                    MovieLocalDataSourceImpl(
                        appDatabase.movieDao(),
                        appDatabase.searchHistoryDao()
                    )
                )
                return MovieViewModel(movieRepository) as T
            }
        }
    }

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter { link ->
            viewModel.openMovieDetail(link)
        }
    }

    private val inputMethodManager: InputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUi()
        setupObserver()

        viewModel.getMovies()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQ_CODE_SEARCH_HISTORY && resultCode == RESULT_OK) {
            data?.getStringExtra(SearchHistoryActivity.EXTRA_QUERY_TEXT)?.let { query ->
                viewModel.query.value = query
                viewModel.searchMovies()
            }
        }
    }

    private fun setupUi() {
        binding.lifecycleOwner = this
        binding.vm = viewModel
        binding.rvMovie.adapter = movieAdapter
    }

    private fun setupObserver() {
        viewModel.showSearchFailedEvent.observe(this) {
            toast(getString(R.string.msg_search_failed))
        }
        viewModel.hideKeyboardEvent.observe(this) {
            inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
        viewModel.movieDetailLink.observe(this) { link ->
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
        }
        viewModel.startSearchHistoryEvent.observe(this) {
            startActivityForResult(
                SearchHistoryActivity.getIntent(this@MovieActivity),
                REQ_CODE_SEARCH_HISTORY
            )
        }
    }

    companion object {

        private const val REQ_CODE_SEARCH_HISTORY = 1001

    }

}