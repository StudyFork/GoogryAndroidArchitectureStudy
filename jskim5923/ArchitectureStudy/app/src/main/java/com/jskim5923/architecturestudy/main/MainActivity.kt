package com.jskim5923.architecturestudy.ui

import android.os.Bundle
import com.jskim5923.architecturestudy.R
import com.jskim5923.architecturestudy.adapter.ViewPagerAdapter
import com.jskim5923.architecturestudy.base.BaseActivity
import com.jskim5923.architecturestudy.databinding.ActivityMainBinding
import com.jskim5923.architecturestudy.main.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override val viewModel = MainViewModel()

    private val viewpagerAdapter by lazy {
        ViewPagerAdapter(supportFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.run {
            tabLayout.setupWithViewPager(viewPager)
            viewPager.adapter = viewpagerAdapter
            mainViewModel = viewModel
        }
    }
}
