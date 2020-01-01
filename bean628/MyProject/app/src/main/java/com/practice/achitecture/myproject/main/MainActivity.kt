package com.practice.achitecture.myproject.main

import android.os.Bundle
import com.practice.achitecture.myproject.R
import com.practice.achitecture.myproject.base.BaseNaverSearchActivity
import com.practice.achitecture.myproject.data.source.NaverRepository
import com.practice.achitecture.myproject.data.source.local.NaverDatabase
import com.practice.achitecture.myproject.data.source.local.NaverLocalDataSourceImpl
import com.practice.achitecture.myproject.data.source.remote.NaverRemoteDataSourceImpl
import com.practice.achitecture.myproject.databinding.ActivityMainBinding
import com.practice.achitecture.myproject.hideSoftKeyboard
import com.practice.achitecture.myproject.history.HistoryActivity
import com.practice.achitecture.myproject.makeToast
import com.practice.achitecture.myproject.network.RetrofitClient
import com.practice.achitecture.myproject.openActivity
import com.practice.achitecture.myproject.util.AppExecutors
import common.NAVER_API_BASE_URL

class MainActivity : BaseNaverSearchActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var mainViewModel = MainViewModel(
            NaverRepository.getInstance(
                NaverRemoteDataSourceImpl(RetrofitClient(NAVER_API_BASE_URL).makeRetrofitServiceForNaver()),
                NaverLocalDataSourceImpl.getInstance(
                    AppExecutors(),
                    NaverDatabase.getInstance(this.applicationContext).naverDao(),
                    this.cacheDir.absolutePath
                )
            )
        ).apply {
            queryEmptyObserver = { makeToast(R.string.toast_empty_word) }
            showProgressBarObserver = {
                if (it) {
                    showLoading()
                } else {
                    hideLoading()
                }
            }
            hideSoftKeyboardObserver =
                { this@MainActivity.hideSoftKeyboard(binding.inputSearchSth) }
            movieOrBookItemsObserver = {
                searchMovieAndBookAdapter?.notifyDataSetChanged(it)
                binding.rvSearchedList.adapter = searchMovieAndBookAdapter
            }
            blogOrNewsItemsObserver = {
                searchBlogAndNewsAdapter?.notifyDataSetChanged(it)
                binding.rvSearchedList.adapter = searchBlogAndNewsAdapter
            }
            stringMessageIdObserver = {
                showToast(it)
            }
            goToHistoryActivityObserver = { openActivity(HistoryActivity::class.java) }
        }

        mainViewModel.loadCache()
        binding.viewModel = mainViewModel

    }

}
