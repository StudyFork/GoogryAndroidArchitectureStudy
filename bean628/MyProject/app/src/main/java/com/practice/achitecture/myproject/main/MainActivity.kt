package com.practice.achitecture.myproject.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practice.achitecture.myproject.R
import com.practice.achitecture.myproject.base.BaseNaverSearchActivity
import com.practice.achitecture.myproject.data.source.NaverRepository
import com.practice.achitecture.myproject.data.source.local.NaverDatabase
import com.practice.achitecture.myproject.data.source.local.NaverLocalDataSourceImpl
import com.practice.achitecture.myproject.data.source.remote.NaverRemoteDataSourceImpl
import com.practice.achitecture.myproject.databinding.ActivityMainBinding
import com.practice.achitecture.myproject.ext.hideSoftKeyboard
import com.practice.achitecture.myproject.ext.makeToast
import com.practice.achitecture.myproject.ext.openActivity
import com.practice.achitecture.myproject.history.HistoryActivity
import com.practice.achitecture.myproject.network.RetrofitClient
import com.practice.achitecture.myproject.util.AppExecutors
import common.NAVER_API_BASE_URL

class MainActivity : BaseNaverSearchActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(
                    NaverRepository.getInstance(
                        NaverRemoteDataSourceImpl(RetrofitClient(NAVER_API_BASE_URL).makeRetrofitServiceForNaver()),
                        NaverLocalDataSourceImpl.getInstance(
                            AppExecutors(),
                            NaverDatabase.getInstance(this@MainActivity.applicationContext).naverDao(),
                            this@MainActivity.cacheDir.absolutePath
                        )
                    )
                ) as T
            }
        })[MainViewModel::class.java]

        binding.rvSearchedList.adapter = searchMovieAndBookAdapter

        mainViewModel.run {
            eventQueryEmpty.observe(this@MainActivity, Observer {
                if (it) {
                    makeToast(R.string.toast_empty_word)
                    this.eventQueryEmpty.value = false
                }
            })

            eventHideSoftKeyboard.observe(this@MainActivity, Observer {
                if (it) {
                    this@MainActivity.hideSoftKeyboard(binding.inputSearchSth)
                    eventHideSoftKeyboard.value = false
                }
            })

            eventStringMessageId.observe(this@MainActivity, Observer {
                if (it != -999) {
                    showToast(it)
                }
            })

            eventGoToHistoryActivity.observe(this@MainActivity, Observer {
                if (it) {
                    openActivity(HistoryActivity::class.java)
                    eventGoToHistoryActivity.value = false
                }
            })

            loadCache()
        }

        binding.lifecycleOwner = this
        binding.viewModel = mainViewModel

    }

}
