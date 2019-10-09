package com.android.studyfork.ui.main

import com.android.studyfork.R
import com.android.studyfork.base.BaseActivity
import com.android.studyfork.databinding.ActivityMainBinding
import com.android.studyfork.ui.main.adapter.ViewPagerAdapter
import com.android.studyfork.ui.main.presenter.MainContract
import com.android.studyfork.ui.main.presenter.MainPresenter

class MainActivity :
    BaseActivity<ActivityMainBinding, MainContract.Presenter>(R.layout.activity_main),
    MainContract.View {

    private val viewPagerAdapter by lazy {
        ViewPagerAdapter(
            supportFragmentManager
        )
    }

    override val presenter by lazy { MainPresenter(this) }

    override fun onStart() {
        super.onStart()
        initViewPager()
        presenter.loadData()
    }

    override fun setViewPagerData(marketData: Pair<List<String>, List<String>>) {
        with(viewPagerAdapter) {
            setTitles(marketData.first.toList())
            setData(marketData.second)
        }
    }

    private fun initViewPager() {
        binding.run {
            pagerContent.adapter = viewPagerAdapter
            layoutTab.setupWithViewPager(this.pagerContent)
            pagerContent.offscreenPageLimit = 3
        }
    }

}

