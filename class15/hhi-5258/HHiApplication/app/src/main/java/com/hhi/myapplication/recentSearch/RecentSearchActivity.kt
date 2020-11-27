package com.hhi.myapplication.recentSearch

import android.os.Bundle
import com.hhi.myapplication.R
import com.hhi.myapplication.base.BaseActivity
import com.hhi.myapplication.data.repository.NaverRepositoryDataSourceImpl
import com.hhi.myapplication.databinding.ActivityRecentSearchBinding
import kotlinx.android.synthetic.main.activity_recent_search.*

class RecentSearchActivity :
    BaseActivity<RecentSearchContract.Presenter, ActivityRecentSearchBinding>(R.layout.activity_recent_search),
    RecentSearchContract.View {
    private val recyclerAdapter = RecentSearchRecyclerAdapter()
    private val recentSearchPresenter = RecentSearchPresenter(
        this,
        NaverRepositoryDataSourceImpl()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpUi()

        recentSearchPresenter.searchRecentQuery()
    }

    private fun setUpUi() {
        recent_search_recyclerview.setHasFixedSize(false)
        recent_search_recyclerview.adapter = recyclerAdapter
    }

    override fun showQueryList(list: List<String>) {
        recyclerAdapter.setQueryList(list)
    }
}