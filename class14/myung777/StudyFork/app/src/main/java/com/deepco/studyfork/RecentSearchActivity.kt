package com.deepco.studyfork

import android.content.Intent
import android.os.Bundle
import com.deepco.studyfork.api.RetrofitService
import com.deepco.studyfork.data.local.LocalMovieDataImpl
import com.deepco.studyfork.data.model.RecentSearchData
import com.deepco.studyfork.data.remote.RemoteMovieDataImpl
import com.deepco.studyfork.data.repository.RepositoryMovieDataImpl
import com.deepco.studyfork.databinding.ActivityRecentSearchBinding
import com.deepco.studyfork.presenter.RecentSearchContract
import com.deepco.studyfork.presenter.RecentSearchPresenter

class RecentSearchActivity :
    BaseActivity<RecentSearchPresenter, ActivityRecentSearchBinding>(R.layout.activity_recent_search),
    RecentSearchContract.View {
    private lateinit var recentSearchRecyclerAdapter: RecentSearchRecyclerAdapter
    private lateinit var api: RetrofitService
    private val recentSearchPresenter by lazy {
        val repositoryMovieDataImpl = RepositoryMovieDataImpl(
            RemoteMovieDataImpl(),
            LocalMovieDataImpl()
        )
        RecentSearchPresenter(this, repositoryMovieDataImpl)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this
        api = RetrofitService.create()

        setRecyclerView()
        recentSearchPresenter.setRecentSearchList()
    }

    private fun setRecyclerView() {
        recentSearchRecyclerAdapter = RecentSearchRecyclerAdapter { query ->
            recentSearchPresenter.getRecentSearchMovie(query)
        }
        binding.recyclerView.adapter = recentSearchRecyclerAdapter
    }

    override fun setRecentSearchList(list: List<RecentSearchData>) {
        recentSearchRecyclerAdapter.setItemList(list)
    }

    override fun onRecentSearchMovie(query: String) {
        setResult(RESULT_OK, Intent().apply {
            putExtra(MainActivity.EXTRA_MOVIE_TITLE, query)
        })
        finish()
    }
}