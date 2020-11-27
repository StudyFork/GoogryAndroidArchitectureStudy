package com.jay.aas.ui.history

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.jay.aas.R
import com.jay.aas.api.RetrofitHelper
import com.jay.aas.base.BaseActivity
import com.jay.aas.data.MovieLocalDataSourceImpl
import com.jay.aas.data.MovieRemoteDataSourceImpl
import com.jay.aas.data.MovieRepositoryImpl
import com.jay.aas.databinding.ActivitySearchHistoryBinding
import com.jay.aas.model.SearchHistory
import com.jay.aas.room.AppDatabase

class SearchHistoryActivity :
    BaseActivity<ActivitySearchHistoryBinding, SearchHistoryContract.Presenter>(
        R.layout.activity_search_history
    ), SearchHistoryContract.View {

    private val searchHistoryAdapter: SearchHistoryAdapter by lazy {
        SearchHistoryAdapter { query ->
            presenter.searchMovies(query)
        }
    }

    override val presenter: SearchHistoryContract.Presenter by lazy {
        val appDatabase = AppDatabase.getInstance(this)
        val movieRepository = MovieRepositoryImpl(
            MovieRemoteDataSourceImpl(RetrofitHelper.movieService),
            MovieLocalDataSourceImpl(appDatabase.movieDao(), appDatabase.searchHistoryDao())
        )
        SearchHistoryPresenter(this, movieRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUi()

        presenter.getSearchHistories()
    }

    private fun setupUi() {
        binding.activity = this
        binding.rvSearchList.adapter = searchHistoryAdapter
    }

    override fun showNoResult() {
        binding.tvNoResult.isVisible = true
        binding.rvSearchList.isGone = true
    }

    override fun showSearchHistoryItems(searchHistories: List<SearchHistory>) {
        binding.tvNoResult.isGone = true
        binding.rvSearchList.isVisible = true
        searchHistoryAdapter.setSearchHistories(searchHistories)
    }

    override fun searchMovies(query: String) {
        setResult(RESULT_OK, Intent().apply {
            putExtra(EXTRA_QUERY_TEXT, query)
        })
        finish()
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