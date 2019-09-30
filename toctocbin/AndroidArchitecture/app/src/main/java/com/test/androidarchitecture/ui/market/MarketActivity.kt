package com.test.androidarchitecture.ui.market

import android.os.Bundle
import android.widget.Toast
import com.test.androidarchitecture.R
import com.test.androidarchitecture.adpter.ViewPagerAdapter
import com.test.androidarchitecture.base.BaseActivity
import com.test.androidarchitecture.data.MarketTitle
import kotlinx.android.synthetic.main.activity_market.*

class MarketActivity : BaseActivity(R.layout.activity_market), MarketContract.View {

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    override val presenter by lazy { MarketPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.getMarketAll()
    }

    override fun start() {
        main_tabLayout.setupWithViewPager(main_viewPager)
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        with(main_viewPager) {
            offscreenPageLimit = 3
            adapter = viewPagerAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.clearDisposable()
    }

    override fun setViewpagerData(list: List<MarketTitle>) {
        viewPagerAdapter.setData(list)
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}