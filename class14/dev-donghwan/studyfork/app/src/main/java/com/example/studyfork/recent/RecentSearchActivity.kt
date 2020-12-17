package com.example.studyfork.recent

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.studyfork.R
import com.example.studyfork.RecentSearchAdapter
import com.example.studyfork.base.BaseActivity
import com.example.studyfork.databinding.ActivityRecentSearchListBinding
import com.example.studyfork.main.MainActivity.Companion.SEARCH_ITEM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecentSearchActivity :
    BaseActivity<ActivityRecentSearchListBinding>(R.layout.activity_recent_search_list) {

    private val viewModel: RecentSearchViewModel by viewModels()

    private lateinit var adapter: RecentSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = RecentSearchAdapter(
            onClick = { s ->
                Intent().putExtra(SEARCH_ITEM, s).apply {
                    setResult(RESULT_OK, this)
                }
                finish()
            }
        )

        binding.vm = viewModel
        binding.rvRecentSearch.adapter = adapter

        viewModel.showListIsEmpty.observe(this) {
            showListIsEmpty()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getRecentSearchList()
    }

    private fun showListIsEmpty() {
        showToast("리스트가 비어 있습니다.")
        finish()
    }
}