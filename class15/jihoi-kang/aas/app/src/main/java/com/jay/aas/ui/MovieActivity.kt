package com.jay.aas.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
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
import com.jay.aas.util.toast
import kotlinx.coroutines.launch

class MovieActivity : BaseActivity<ActivityMovieBinding, MovieContract.Presenter>(),
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
        val localDataSource = MovieLocalDataSourceImpl(AppDatabase.getInstance(this).movieDao())
        MovieRepositoryImpl(remoteDataSource, localDataSource)
    }
    override val presenter: MovieContract.Presenter by lazy {
        MoviePresenter(this, movieRepository)
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMovieBinding =
        ActivityMovieBinding.inflate(inflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUi()

        lifecycleScope.launch {
            presenter.getMovies()
        }
    }

    private fun setupUi() {
        progressBar = binding.pbLoading
        binding.rvMovie.adapter = movieAdapter

        binding.evSearch.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                lifecycleScope.launch {
                    presenter.searchMovies(v.text.toString())
                }
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        binding.ivSearch.setOnClickListener {
            lifecycleScope.launch {
                presenter.searchMovies(binding.evSearch.text.toString())
            }
        }
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

}