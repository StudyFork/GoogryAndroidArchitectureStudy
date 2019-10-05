package com.test.androidarchitecture.ui.market

import com.test.androidarchitecture.R
import com.test.androidarchitecture.adpter.ViewPagerAdapter
import com.test.androidarchitecture.base.BaseActivity
import com.test.androidarchitecture.data.MarketTitle
import kotlinx.android.synthetic.main.activity_market.*

class MarketActivity : BaseActivity<MarketContract.Presenter>(R.layout.activity_market), MarketContract.View {

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    override val presenter = MarketPresenter(this)

    override fun start() {
        main_tabLayout.setupWithViewPager(main_viewPager)
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        with(main_viewPager) {
            offscreenPageLimit = 3
            adapter = viewPagerAdapter
        }
        presenter.getMarketAll()
    }

    override fun setViewpagerData(list: List<MarketTitle>) {
        viewPagerAdapter.setData(list)
    }
}