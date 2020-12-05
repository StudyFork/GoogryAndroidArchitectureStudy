package com.jay.aas.ui.history

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.Observable
import com.jay.aas.R
import com.jay.aas.api.RetrofitHelper
import com.jay.aas.base.BaseActivity
import com.jay.aas.data.MovieLocalDataSourceImpl
import com.jay.aas.data.MovieRemoteDataSourceImpl
import com.jay.aas.data.MovieRepository
import com.jay.aas.data.MovieRepositoryImpl
import com.jay.aas.databinding.ActivitySearchHistoryBinding
import com.jay.aas.room.AppDatabase

class SearchHistoryActivity :
    BaseActivity<ActivitySearchHistoryBinding>(
        R.layout.activity_search_history
    ) {

    private val searchHistoryAdapter: SearchHistoryAdapter by lazy {
        SearchHistoryAdapter { query ->
            viewModel.searchMovies(query)
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
    private val viewModel: SearchHistoryViewModel by lazy {
        SearchHistoryViewModel(movieRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUi()
        setupObserver()

        viewModel.getSearchHistories()
    }

    private fun setupUi() {
        binding.vm = viewModel
        binding.rvSearchList.adapter = searchHistoryAdapter
    }

    private fun setupObserver() {
        viewModel.finishEvent
            .addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    finish()
                }
            })

        viewModel.searchQuery
            .addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    setResult(RESULT_OK, Intent().apply {
                        putExtra(EXTRA_QUERY_TEXT, viewModel.searchQuery.get())
                    })
                    finish()
                }
            })
    }

    companion object {

        const val EXTRA_QUERY_TEXT = "android.intent.extra.QUERY_TEXT"

        fun getIntent(
            context: Context
        ) = Intent(context, SearchHistoryActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }

    }
}