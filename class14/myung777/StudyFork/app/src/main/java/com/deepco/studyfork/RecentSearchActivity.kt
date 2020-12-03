package com.deepco.studyfork

import android.content.Intent
import android.os.Bundle
import androidx.databinding.Observable
import com.deepco.studyfork.databinding.ActivityRecentSearchBinding
import com.deepco.studyfork.viewmodel.RecentSearchViewModel

class RecentSearchActivity :
    BaseActivity<ActivityRecentSearchBinding>(R.layout.activity_recent_search) {
    private lateinit var recentSearchRecyclerAdapter: RecentSearchRecyclerAdapter
    val recentSearchViewModel = RecentSearchViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = recentSearchViewModel
        setRecyclerView()
        setObserver()
        recentSearchViewModel.getSearchHistories()
    }

    private fun setRecyclerView() {
        recentSearchRecyclerAdapter = RecentSearchRecyclerAdapter { query ->
            recentSearchViewModel.setRecentSearchTitle(query)
        }
        binding.recyclerView.adapter = recentSearchRecyclerAdapter
    }

    private fun setObserver() {
        recentSearchViewModel.searchHistoryItems.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                recentSearchRecyclerAdapter.setItemList(
                    recentSearchViewModel.searchHistoryItems.get()!!.toList()
                )
            }
        })
        recentSearchViewModel.recentSearchTitle.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                setResult(RESULT_OK, Intent().apply {
                    putExtra(
                        MainActivity.EXTRA_MOVIE_TITLE,
                        recentSearchViewModel.recentSearchTitle.get().toString()
                    )
                })
                finish()
            }
        })
    }
}