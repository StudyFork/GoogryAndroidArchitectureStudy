package com.jskim5923.architecturestudy.main

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.jskim5923.architecturestudy.R
import com.jskim5923.architecturestudy.adapter.ViewPagerAdapter
import com.jskim5923.architecturestudy.api.ApiManager
import com.jskim5923.architecturestudy.base.BaseActivity
import com.jskim5923.architecturestudy.databinding.ActivityMainBinding
import com.jskim5923.architecturestudy.model.data.source.RemoteDataSourceImpl
import com.jskim5923.architecturestudy.model.data.source.RepositoryImpl

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override val viewModel by lazy {
        ViewModelProviders.of(
            this,
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return RepositoryImpl(RemoteDataSourceImpl(ApiManager.coinApi)) as T
                }
            }
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
