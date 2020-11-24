package com.deepco.studyfork

import android.content.Intent
import android.os.Bundle
import com.deepco.studyfork.api.RetrofitService
import com.deepco.studyfork.data.local.LocalMovieDataImpl
import com.deepco.studyfork.data.remote.RemoteMovieDataImpl
import com.deepco.studyfork.data.repository.RepositoryMovieDataImpl
import com.deepco.studyfork.presenter.RecentSearchContract
import com.deepco.studyfork.presenter.RecentSearchPresenter
import kotlinx.android.synthetic.main.activity_main.*

class RecentSearchActivity : BaseActivity<RecentSearchPresenter>(), RecentSearchContract.View {
    private lateinit var recentSearchRecyclerAdapter: RecentSearchRecyclerAdapter
    private lateinit var api: RetrofitService
    private val mainPresenter by lazy {
        val repositoryMovieDataImpl = RepositoryMovieDataImpl(
            RemoteMovieDataImpl(api),
            LocalMovieDataImpl()
        )
        RecentSearchPresenter(this, repositoryMovieDataImpl)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recent_search)
        api = RetrofitService.create()

        setRecyclerView()
        mainPresenter.setRecentSearchList()
    }

    private fun setRecyclerView() {
        recentSearchRecyclerAdapter = RecentSearchRecyclerAdapter { query ->
            mainPresenter.getRecentSearchMovie(query)
        }
        recycler_view.adapter = recentSearchRecyclerAdapter
    }

    override fun setRecentSearchList(list: List<String>) {
        recentSearchRecyclerAdapter.setItemList(list)
    }

    override fun onRecentSearchMovie(query: String) {
        setResult(RESULT_OK, Intent().apply {
            putExtra(EXTRA_MOVIE_TITLE, query)
        })
        finish()
    }
}