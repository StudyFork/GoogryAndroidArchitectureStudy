package com.android.studyfork.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.android.studyfork.R
import com.android.studyfork.base.BaseActivity
import com.android.studyfork.databinding.ActivityMainBinding
import com.android.studyfork.ui.main.adapter.ViewPagerAdapter
import com.android.studyfork.ui.main.viewmodel.MainViewModel

class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val viewModel by lazy {
        ViewModelProviders.of(
            this
        )[MainViewModel::class.java]
    }

    private val viewPagerAdapter by lazy {
        ViewPagerAdapter(
            supportFragmentManager
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

