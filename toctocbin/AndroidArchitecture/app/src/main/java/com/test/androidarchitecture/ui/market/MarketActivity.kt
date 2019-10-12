package com.test.androidarchitecture.ui.market

import com.test.androidarchitecture.R
import com.test.androidarchitecture.adpter.ViewPagerAdapter
import com.test.androidarchitecture.base.BaseActivity
import com.test.androidarchitecture.data.MarketTitle
import com.test.androidarchitecture.databinding.ActivityMarketBinding

class MarketActivity : BaseActivity<MarketContract.Presenter, ActivityMarketBinding>(R.layout.activity_market), MarketContract.View {

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    override val presenter = MarketPresenter(this)

    override fun start() {
        binding.mainTabLayout.setupWithViewPager(binding.mainViewPager)
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        with(binding.mainViewPager) {
            offscreenPageLimit = 3
            adapter = viewPagerAdapter
        }
        presenter.getMarketAll()
    }

    override fun setViewpagerData(list: List<MarketTitle>) {
        viewPagerAdapter.setData(list)
    }
}