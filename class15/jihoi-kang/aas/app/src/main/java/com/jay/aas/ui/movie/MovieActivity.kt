package com.jay.aas.ui.movie

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.jay.aas.R
import com.jay.aas.api.RetrofitHelper
import com.jay.aas.base.BaseActivity
import com.jay.aas.data.MovieLocalDataSourceImpl
import com.jay.aas.data.MovieRemoteDataSourceImpl
import com.jay.aas.data.MovieRepository
import com.jay.aas.data.MovieRepositoryImpl
import com.jay.aas.databinding.ActivityMovieBinding
import com.jay.aas.model.Movie
import com.jay.aas.room.AppDatabase
import com.jay.aas.ui.history.SearchHistoryActivity
import com.jay.aas.ui.history.SearchHistoryActivity.Companion.EXTRA_QUERY_TEXT
import com.jay.aas.util.toast
import kotlinx.coroutines.launch

class MovieActivity :
    BaseActivity<ActivityMovieBinding, MovieContract.Presenter>(R.layout.activity_movie),
    MovieContract.View {

    private val TAG = this::class.java.simpleName

    private val inputMethodManager: InputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }
    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter { link ->
            presenter.openMovieDetail(link)
        }
    }
    private val movieRepository: MovieRepository by lazy {
        val remoteDataSource = MovieRemoteDataSourceImpl(RetrofitHelper.movieService)
        val appDatabase = AppDatabase.getInstance(this)
        val localDataSource = MovieLocalDataSourceImpl(
            appDatabase.movieDao(),
            appDatabase.searchHistoryDao()
        )
        MovieRepositoryImpl(remoteDataSource, localDataSource)
    }
    override val presenter: MovieContract.Presenter by lazy {
        MoviePresenter(this, movieRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUi()

        lifecycleScope.launch {
            presenter.getMovies()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQ_CODE_SEARCH_HISTORY && resultCode == RESULT_OK) {
            data?.getStringExtra(EXTRA_QUERY_TEXT)?.let { query ->
                searchMovies(query)
            }
        }
    }

    private fun setupUi() {
        binding.activity = this
        progressBar = binding.pbLoading
        binding.rvMovie.adapter = movieAdapter
    }

    fun searchMovies(query: String) {
        lifecycleScope.launch {
            presenter.searchMovies(query)
        }
    }

    fun searchHistories() {
        startActivityForResult(SearchHistoryActivity.getIntent(this), REQ_CODE_SEARCH_HISTORY)
    }

    override fun hideKeyboard() {
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    override fun showSearchFailed() {
        toast(getString(R.string.msg_search_failed))
    }

    override fun showNoResult() {
        binding.tvNoResult.isVisible = true
        binding.rvMovie.isGone = true
    }

    override fun showMovieItems(movies: List<Movie>) {
        binding.tvNoResult.isGone = true
        binding.rvMovie.isVisible = true
        movieAdapter.setMovies(movies)
    }

    override fun openMovieDetail(link: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
    }

    companion object {

        private const val REQ_CODE_SEARCH_HISTORY = 1001

    }

}