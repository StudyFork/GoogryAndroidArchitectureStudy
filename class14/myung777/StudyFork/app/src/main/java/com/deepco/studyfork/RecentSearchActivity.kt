package com.deepco.studyfork

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.deepco.studyfork.databinding.ActivityRecentSearchBinding
import com.deepco.studyfork.viewmodel.RecentSearchViewModel

class RecentSearchActivity :
    BaseActivity<ActivityRecentSearchBinding>(R.layout.activity_recent_search) {

    private val recentSearchViewModel by viewModels<RecentSearchViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return RecentSearchViewModel() as T
            }
        }
    }
    private val recentSearchRecyclerAdapter by lazy {
        RecentSearchRecyclerAdapter(recentSearchViewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = recentSearchViewModel
        binding.lifecycleOwner = this
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