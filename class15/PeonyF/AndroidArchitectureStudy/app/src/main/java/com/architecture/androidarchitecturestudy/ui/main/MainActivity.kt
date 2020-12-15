package com.architecture.androidarchitecturestudy.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.architecture.androidarchitecturestudy.R
import com.architecture.androidarchitecturestudy.databinding.ActivityMainBinding
import com.architecture.androidarchitecturestudy.ui.base.BaseActivity
import com.architecture.androidarchitecturestudy.ui.searchhistory.SearchHistoryActivity
import com.architecture.androidarchitecturestudy.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.mainViewModel = mainViewModel
        viewModelCallback()
    }

    private fun viewModelCallback() {
        mainViewModel.msg.observe(this, {
            when (it) {
                "success" -> toast(R.string.network_success)
                "emptyKeyword" -> toast(R.string.keyword_empty)
                "fail" -> toast(R.string.network_error)
                "emptyResult" -> toast(R.string.keyword_result_empty)
            }
        })

        mainViewModel.callSearchHistory.observe(this, {
            searchHistory()
        })
    }

    fun searchHistory() {
        val intent = Intent(this, SearchHistoryActivity::class.java)
        startActivityForResult(intent, REQ_CODE_SEARCH_HISTORY)
    }

    companion object {
        const val REQ_CODE_SEARCH_HISTORY = 101
    }
}