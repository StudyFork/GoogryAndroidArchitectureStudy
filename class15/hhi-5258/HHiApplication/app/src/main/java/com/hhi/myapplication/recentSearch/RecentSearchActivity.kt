package com.hhi.myapplication.recentSearch

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hhi.myapplication.R
import com.hhi.myapplication.base.BaseActivity
import com.hhi.myapplication.data.repository.NaverRepositoryDataSourceImpl
import com.hhi.myapplication.databinding.ActivityRecentSearchBinding
import kotlinx.android.synthetic.main.activity_recent_search.*

class RecentSearchActivity : BaseActivity<RecentSearchContract.Presenter>(),
    RecentSearchContract.View {
    private lateinit var binding: ActivityRecentSearchBinding
    private val recyclerAdapter = RecentSearchRecyclerAdapter()
    private val recentSearchPresenter = RecentSearchPresenter(
        this,
        NaverRepositoryDataSourceImpl()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recent_search)

        setUpUI()

        recentSearchPresenter.searchRecentQuery()
    }

    private fun setUpUI() {
        recent_search_recyclerview.setHasFixedSize(false)
        recent_search_recyclerview.adapter = recyclerAdapter
    }

    override fun showQueryList(list: List<String>) {
        recyclerAdapter.setQueryList(list)
    }
}