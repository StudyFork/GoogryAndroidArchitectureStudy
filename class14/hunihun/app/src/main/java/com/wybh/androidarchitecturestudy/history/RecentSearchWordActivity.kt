package com.wybh.androidarchitecturestudy.history

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.wybh.androidarchitecturestudy.base.BaseActivity
import com.wybh.androidarchitecturestudy.adapter.HistoryAdapter
import com.wybh.androidarchitecturestudy.R
import com.wybh.androidarchitecturestudy.databinding.ActivityRecentSearchWordBinding
import com.wybh.androidarchitecturestudy.util.HorizontalSpaceItemDecoration

class RecentSearchWordActivity :
    BaseActivity<ActivityRecentSearchWordBinding, RecentSearchWordViewModel>(R.layout.activity_recent_search_word) {
    override val vm by viewModels<RecentSearchWordViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return RecentSearchWordViewModel() as T
            }
        }
    }

    private val historyAdapter = HistoryAdapter {
        setResult(RESULT_OK, Intent().putExtra("SEARCH_WORD", it))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAdapter()
        initObserve()
    }

    private fun initAdapter() {
        binding.rvHistory.run {
            adapter = historyAdapter
            addItemDecoration(HorizontalSpaceItemDecoration(20))
            layoutManager = LinearLayoutManager(this@RecentSearchWordActivity).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
            }
        }
    }

    private fun initObserve() {
        vm.wordList.observe(this) {
            historyAdapter.addList(it)
        }
    }
}