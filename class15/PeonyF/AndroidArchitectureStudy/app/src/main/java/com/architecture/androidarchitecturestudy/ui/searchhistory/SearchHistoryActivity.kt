package com.architecture.androidarchitecturestudy.ui.searchhistory


import android.os.Bundle
import com.architecture.androidarchitecturestudy.R
import com.architecture.androidarchitecturestudy.data.local.MovieLocalDataSourceImpl
import com.architecture.androidarchitecturestudy.data.remote.MovieRemoteDataSourceImpl
import com.architecture.androidarchitecturestudy.data.repository.MovieRepositoryImpl
import com.architecture.androidarchitecturestudy.databinding.ActivitySearchHistoryBinding
import com.architecture.androidarchitecturestudy.ui.base.BaseActivity
import com.architecture.androidarchitecturestudy.webservice.ApiClient

class SearchHistoryActivity :
    BaseActivity<ActivitySearchHistoryBinding>(R.layout.activity_search_history) {

    private val viewModel by lazy {
        val remoteDataSourceImpl = MovieRemoteDataSourceImpl(ApiClient.NETWORK_SERVICE)
        val localDataSourceImpl = MovieLocalDataSourceImpl()
        SearchHistoryViewModel(MovieRepositoryImpl(remoteDataSourceImpl, localDataSourceImpl))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.searchActivity = this
        binding.viewModel = viewModel
        viewModel.getRecentKeywordList()
    }
}