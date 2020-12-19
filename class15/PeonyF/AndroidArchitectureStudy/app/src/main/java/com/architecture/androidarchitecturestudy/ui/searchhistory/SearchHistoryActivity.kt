package com.architecture.androidarchitecturestudy.ui.searchhistory


import android.os.Bundle
import androidx.activity.viewModels
import com.architecture.androidarchitecturestudy.R
import com.architecture.androidarchitecturestudy.databinding.ActivitySearchHistoryBinding
import com.architecture.androidarchitecturestudy.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchHistoryActivity :
    BaseActivity<ActivitySearchHistoryBinding>(R.layout.activity_search_history) {
    private val searchHistoryViewModel: SearchHistoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.searchHistoryViewModel = searchHistoryViewModel
        searchHistoryViewModel.getRecentKeywordList()
    }
}