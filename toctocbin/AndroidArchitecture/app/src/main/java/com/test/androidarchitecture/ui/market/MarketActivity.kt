package com.test.androidarchitecture.ui.market

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.test.androidarchitecture.R
import com.test.androidarchitecture.adpter.ViewPagerAdapter
import com.test.androidarchitecture.base.BaseActivity
import com.test.androidarchitecture.databinding.ActivityMarketBinding
import javax.inject.Inject

class MarketActivity
    : BaseActivity<ActivityMarketBinding, MarketViewModel>(R.layout.activity_market) {

    override val vm: MarketViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(MarketViewModel::class.java)
    }
    private lateinit var viewPagerAdapter: ViewPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.mainTabLayout.setupWithViewPager(binding.mainViewPager)
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        with(binding.mainViewPager) {
            offscreenPageLimit = 3
            adapter = viewPagerAdapter
        }
        setObservableCallBack()
    }

    private fun setObservableCallBack() {
        vm.marketTitle.observe(this, Observer { viewPagerAdapter.setData(it) })
    }
}