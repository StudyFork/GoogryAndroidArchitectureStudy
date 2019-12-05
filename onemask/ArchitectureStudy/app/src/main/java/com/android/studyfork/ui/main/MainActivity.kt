package com.android.studyfork.ui.main

import androidx.lifecycle.ViewModelProviders
import com.android.studyfork.R
import com.android.studyfork.base.BaseActivity
import com.android.studyfork.databinding.ActivityMainBinding
import com.android.studyfork.repository.UpbitRepositoryImpl
import com.android.studyfork.ui.main.adapter.ViewPagerAdapter
import com.android.studyfork.ui.main.viewmodel.MainViewModel
import com.android.studyfork.ui.tickerlist.viewmodel.MainViewModelFactory
import javax.inject.Inject

class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    @Inject
    lateinit var upbitRepositoryImpl: UpbitRepositoryImpl

    private val viewModelFactory by lazy {
        MainViewModelFactory(upbitRepositoryImpl)
    }

    override val viewModel by lazy {
        ViewModelProviders.of(
            this,
            viewModelFactory
        )[MainViewModel::class.java]
    }

    private val viewPagerAdapter by lazy {
        ViewPagerAdapter(
            supportFragmentManager
        )
    }

    override fun onStart() {
        super.onStart()
        initViewPager()
    }


    private fun initViewPager() {
        binding.run {
            pagerContent.adapter = viewPagerAdapter
            layoutTab.setupWithViewPager(this.pagerContent)
            pagerContent.offscreenPageLimit = 3
            mainViewModel = viewModel

        }
    }

}

