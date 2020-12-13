package com.deepco.studyfork

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.observe
import com.deepco.studyfork.databinding.ActivityRecentSearchBinding
import com.deepco.studyfork.viewmodel.RecentSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecentSearchActivity :
    BaseActivity<ActivityRecentSearchBinding>(R.layout.activity_recent_search) {

    private val recentSearchViewModel: RecentSearchViewModel by viewModels()

    private val recentSearchRecyclerAdapter by lazy {
        RecentSearchRecyclerAdapter(recentSearchViewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = recentSearchViewModel
        setRecyclerView()
        setObserver()
    }

    private fun setRecyclerView() {
        binding.recyclerView.adapter = recentSearchRecyclerAdapter
    }

    private fun setObserver() {
        recentSearchViewModel.recentSearchTitle.observe(this) {
            setResult(RESULT_OK, Intent().apply {
                putExtra(
                    MainActivity.EXTRA_MOVIE_TITLE,
                    it
                )
            })
            finish()
        }
    }
}