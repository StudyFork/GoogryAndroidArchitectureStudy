package com.jskim5923.architecturestudy.ui

import com.jskim5923.architecturestudy.R
import com.jskim5923.architecturestudy.adapter.ViewPagerAdapter
import com.jskim5923.architecturestudy.base.BaseActivity
import com.jskim5923.architecturestudy.main.MainContract
import com.jskim5923.architecturestudy.main.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(R.layout.activity_main), MainContract.View {
    override val presenter = MainPresenter(this)

    private val viewpagerAdapter by lazy {
        ViewPagerAdapter(supportFragmentManager)
    }

    override fun initView() {
        with(viewPager) {
            tabLayout.setupWithViewPager(this)
            adapter = viewpagerAdapter
        }
        presenter.loadMarketList()
    }

    override fun updateViewpager(marketList: List<String>) {
        viewpagerAdapter.updateItem(marketList)
    }
}
