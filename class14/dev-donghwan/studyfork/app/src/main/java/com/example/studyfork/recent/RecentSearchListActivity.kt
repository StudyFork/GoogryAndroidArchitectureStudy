package com.example.studyfork.recent

import android.content.Intent
import android.os.Bundle
import com.example.studyfork.R
import com.example.studyfork.RecentSearchAdapter
import com.example.studyfork.base.BaseActivity
import com.example.studyfork.data.local.LocalDataSourceImpl
import com.example.studyfork.data.remote.RemoteDataSourceImpl
import com.example.studyfork.data.repository.RepositoryImpl
import com.example.studyfork.databinding.ActivityRecentSearchListBinding
import com.example.studyfork.main.MainActivity.Companion.SEARCH_ITEM

class RecentSearchListActivity :
    BaseActivity<ActivityRecentSearchListBinding>(R.layout.activity_recent_search_list),
    RecentSearchListContract.View {

    private val presenter: RecentSearchListContract.Presenter by lazy {
        RecentSearchListPresenter(
            this,
            RepositoryImpl(
                RemoteDataSourceImpl(),
                LocalDataSourceImpl(getSharedPreferences("local", MODE_PRIVATE))
            )
        )
    }

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

        binding.activity = this
        binding.rvRecentSearch.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        presenter.getRecentSearchList()
    }

    override fun showListIsEmpty() {
        showToast("리스트가 비어 있습니다.")
        finish()
    }

    override fun showRecentSearchList(list: List<String>) {
        adapter.setItems(list)
    }
}