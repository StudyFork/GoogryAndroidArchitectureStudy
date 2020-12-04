package com.architecture.androidarchitecturestudy.ui.main


import android.content.Intent
import android.os.Bundle
import androidx.databinding.Observable
import com.architecture.androidarchitecturestudy.R
import com.architecture.androidarchitecturestudy.data.local.MovieLocalDataSourceImpl
import com.architecture.androidarchitecturestudy.data.remote.MovieRemoteDataSourceImpl
import com.architecture.androidarchitecturestudy.data.repository.MovieRepositoryImpl
import com.architecture.androidarchitecturestudy.databinding.ActivityMainBinding
import com.architecture.androidarchitecturestudy.ui.base.BaseActivity
import com.architecture.androidarchitecturestudy.ui.searchhistory.SearchHistoryActivity
import com.architecture.androidarchitecturestudy.util.toast
import com.architecture.androidarchitecturestudy.webservice.ApiClient

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by lazy {
        val remoteDataSourceImpl = MovieRemoteDataSourceImpl(ApiClient.NETWORK_SERVICE)
        val localDataSourceImpl = MovieLocalDataSourceImpl()
        MainViewModel(MovieRepositoryImpl(remoteDataSourceImpl, localDataSourceImpl))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        viewModelCallback()
    }

    private fun viewModelCallback() {
        viewModel.showToastMsg.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                viewModel.msg.get()?.let {
                    when (it) {
                        "success" -> toast(R.string.network_success)
                        "emptyKeyword" -> toast(R.string.keyword_empty)
                        "fail" -> toast(R.string.network_error)
                        "emptyResult" -> toast(R.string.keyword_result_empty)
                    }
                }
            }
        })
    }

    fun searchHistory() {
        val intent = Intent(this, SearchHistoryActivity::class.java)
        startActivityForResult(intent, REQ_CODE_SEARCH_HISTORY)
    }

    companion object {
        const val REQ_CODE_SEARCH_HISTORY = 101
    }
}