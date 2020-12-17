package com.jay.aas.ui.history

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.jay.aas.R
import com.jay.aas.base.BaseActivity
import com.jay.aas.databinding.ActivitySearchHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchHistoryActivity : BaseActivity<ActivitySearchHistoryBinding>(
    R.layout.activity_search_history
) {

    private val viewModel: SearchHistoryViewModel by viewModels()

    private val searchHistoryAdapter: SearchHistoryAdapter by lazy {
        SearchHistoryAdapter { query ->
            viewModel.searchMovies(query)
        }
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
        viewModel.finishEvent.observe(this) {
            finish()
        }
        viewModel.searchQuery.observe(this) { query ->
            setResult(RESULT_OK, Intent().apply {
                putExtra(EXTRA_QUERY_TEXT, query)
            })
            finish()
        }
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