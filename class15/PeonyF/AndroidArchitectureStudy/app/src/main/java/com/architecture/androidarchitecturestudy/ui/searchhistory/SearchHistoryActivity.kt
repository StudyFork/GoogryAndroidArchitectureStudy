package com.architecture.androidarchitecturestudy.ui.searchhistory

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.architecture.androidarchitecturestudy.R
import com.architecture.androidarchitecturestudy.data.local.MovieLocalDataSourceImpl
import com.architecture.androidarchitecturestudy.data.model.SearchHistoryEntity
import com.architecture.androidarchitecturestudy.data.remote.MovieRemoteDataSourceImpl
import com.architecture.androidarchitecturestudy.data.repository.MovieRepositoryImpl
import com.architecture.androidarchitecturestudy.databinding.ActivitySearchHistoryBinding
import com.architecture.androidarchitecturestudy.ui.base.BaseActivity
import com.architecture.androidarchitecturestudy.ui.main.MainActivity
import com.architecture.androidarchitecturestudy.webservice.ApiClient

class SearchHistoryActivity :
    BaseActivity<SearchHistoryContract.Presenter>(), SearchHistoryContract.View {
    private lateinit var searchHistoryAdapter: SearchHistoryAdapter
    private lateinit var searchBinding: ActivitySearchHistoryBinding

    private val searchPresenter: SearchHistoryContract.Presenter by lazy {
        val repositoryMovieDataImpl =
            MovieRepositoryImpl(
                MovieRemoteDataSourceImpl(ApiClient.NETWORK_SERVICE), MovieLocalDataSourceImpl()
            )
        SearchHistoryPresenter(this, repositoryMovieDataImpl)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchBinding = DataBindingUtil.setContentView(this, R.layout.activity_search_history)
        searchBinding.searchActivity = this
        initRecyclerView()
        searchPresenter.setSearchHistoryList()
    }

    private fun initRecyclerView() {
        searchHistoryAdapter = SearchHistoryAdapter { keyword ->
            searchPresenter.getSearchHistoryMovie(keyword)
        }
        searchBinding.rvSearchList.adapter = searchHistoryAdapter
    }

    override fun setSearchHistoryList(list: List<SearchHistoryEntity>) {
        searchHistoryAdapter.setSearchHistories(list)
    }

    override fun onSearchHistoryMovie(keyword: String) {
        setResult(RESULT_OK, Intent().apply {
            putExtra(MainActivity.MOVIE_KEYWORD, keyword)
        })
        finish()
    }
}







