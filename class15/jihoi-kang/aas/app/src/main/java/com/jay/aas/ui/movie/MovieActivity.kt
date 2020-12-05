package com.jay.aas.ui.movie

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.databinding.Observable
import com.jay.aas.R
import com.jay.aas.api.RetrofitHelper
import com.jay.aas.base.BaseActivity
import com.jay.aas.data.MovieLocalDataSourceImpl
import com.jay.aas.data.MovieRemoteDataSourceImpl
import com.jay.aas.data.MovieRepository
import com.jay.aas.data.MovieRepositoryImpl
import com.jay.aas.databinding.ActivityMovieBinding
import com.jay.aas.room.AppDatabase
import com.jay.aas.ui.history.SearchHistoryActivity
import com.jay.aas.util.toast

class MovieActivity :
    BaseActivity<ActivityMovieBinding>(
        R.layout.activity_movie
    ) {

    private val TAG = this::class.java.simpleName

    private val inputMethodManager: InputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }
    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter { link ->
            viewModel.openMovieDetail(link)
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
    private val viewModel: MovieViewModel by lazy {
        MovieViewModel(movieRepository)
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
                viewModel.query.set(query)
                viewModel.searchMovies()
            }
        }
    }

    private fun setupUi() {
        binding.vm = viewModel
        binding.rvMovie.adapter = movieAdapter
    }

    private fun setupObserver() {
        viewModel.showSearchFailedEvent
            .addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    toast(getString(R.string.msg_search_failed))
                }
            })
        viewModel.hideKeyboardEvent
            .addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
                }
            })
        viewModel.movieDetailLink
            .addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(viewModel.movieDetailLink.get())
                        )
                    )
                }
            })
        viewModel.startSearchHistoryEvent
            .addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    startActivityForResult(
                        SearchHistoryActivity.getIntent(this@MovieActivity),
                        REQ_CODE_SEARCH_HISTORY
                    )
                }
            })
    }

    companion object {

        private const val REQ_CODE_SEARCH_HISTORY = 1001

    }

}