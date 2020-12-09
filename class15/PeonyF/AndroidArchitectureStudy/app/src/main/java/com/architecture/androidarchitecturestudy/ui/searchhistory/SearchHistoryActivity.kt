package com.architecture.androidarchitecturestudy.ui.searchhistory


import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.architecture.androidarchitecturestudy.R
import com.architecture.androidarchitecturestudy.data.local.MovieLocalDataSourceImpl
import com.architecture.androidarchitecturestudy.data.remote.MovieRemoteDataSourceImpl
import com.architecture.androidarchitecturestudy.data.repository.MovieRepositoryImpl
import com.architecture.androidarchitecturestudy.databinding.ActivitySearchHistoryBinding
import com.architecture.androidarchitecturestudy.ui.base.BaseActivity
import com.architecture.androidarchitecturestudy.webservice.ApiClient

class SearchHistoryActivity :
    BaseActivity<ActivitySearchHistoryBinding>(R.layout.activity_search_history) {

    private val searchHistoryViewModel by viewModels<SearchHistoryViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val remoteDataSourceImpl = MovieRemoteDataSourceImpl(ApiClient.NETWORK_SERVICE)
                val localDataSourceImpl = MovieLocalDataSourceImpl()
                val movieRepository = MovieRepositoryImpl(remoteDataSourceImpl, localDataSourceImpl)
                return SearchHistoryViewModel(movieRepository) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.searchHistoryViewModel = searchHistoryViewModel
        binding.lifecycleOwner = this
        searchHistoryViewModel.getRecentKeywordList()
    }
}