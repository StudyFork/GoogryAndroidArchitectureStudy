package com.jay.aas.ui.history

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.Observable
import com.jay.aas.R
import com.jay.aas.base.BaseActivity
import com.jay.aas.databinding.ActivitySearchHistoryBinding

class SearchHistoryActivity :
    BaseActivity<ActivitySearchHistoryBinding, SearchHistoryViewModel>(
        R.layout.activity_search_history
    ) {

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