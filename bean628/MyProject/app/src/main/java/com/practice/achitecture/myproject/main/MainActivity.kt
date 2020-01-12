package com.practice.achitecture.myproject.main

import android.os.Bundle
import androidx.lifecycle.Observer
import com.practice.achitecture.myproject.R
import com.practice.achitecture.myproject.base.BaseNaverSearchActivity
import com.practice.achitecture.myproject.databinding.ActivityMainBinding
import com.practice.achitecture.myproject.ext.hideSoftKeyboard
import com.practice.achitecture.myproject.ext.makeToast
import com.practice.achitecture.myproject.ext.openActivity
import com.practice.achitecture.myproject.history.HistoryActivity
import org.koin.android.viewmodel.ext.android.getViewModel

class MainActivity : BaseNaverSearchActivity<ActivityMainBinding>(R.layout.activity_main) {

//    val remote : NaverRemoteDataSourceImpl by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.rvSearchedList.adapter = searchNaverAdapter
        val mainViewModel: MainViewModel = getViewModel()
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
