package com.jskim5923.architecturestudy.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.jskim5923.architecturestudy.R
import com.jskim5923.architecturestudy.adapter.ViewPagerAdapter
import com.jskim5923.architecturestudy.base.BaseActivity
import com.jskim5923.architecturestudy.databinding.ActivityMainBinding
import com.jskim5923.architecturestudy.di.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    override val viewModel by lazy {
        ViewModelProviders.of(
            this,
            viewModelFactory
        )[MainViewModel::class.java]
    }

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
