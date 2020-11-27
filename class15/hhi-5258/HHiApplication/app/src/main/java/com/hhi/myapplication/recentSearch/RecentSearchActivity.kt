package com.hhi.myapplication.recentSearch

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.hhi.myapplication.R
import com.hhi.myapplication.base.BaseActivity
import com.hhi.myapplication.data.repository.NaverRepositoryDataSourceImpl
import com.hhi.myapplication.databinding.ActivityRecentSearchBinding
import kotlinx.android.synthetic.main.activity_recent_search.*

class RecentSearchActivity :
    BaseActivity<RecentSearchContract.Presenter, ActivityRecentSearchBinding>(R.layout.activity_recent_search),
    RecentSearchContract.View {
<<<<<<< HEAD
    private val recyclerAdapter = RecentSearchRecyclerAdapter(
        onClick = { query ->
            val intent = Intent()
            intent.putExtra("query", query)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    )

=======
    private val recyclerAdapter = RecentSearchRecyclerAdapter()
>>>>>>> cb66eda56bd7bdeca386021dd2af7fcf23335b4f
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