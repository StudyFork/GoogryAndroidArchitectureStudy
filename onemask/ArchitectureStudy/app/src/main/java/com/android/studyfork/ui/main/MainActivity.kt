package com.android.studyfork.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.studyfork.R
import com.android.studyfork.network.model.MarketResponse
import com.android.studyfork.ui.main.adapter.ViewPagerAdapter
import com.android.studyfork.ui.main.presenter.MainContract
import com.android.studyfork.ui.main.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private val viewPagerAdapter by lazy {
        ViewPagerAdapter(
            supportFragmentManager
        )
    }
    private val presenter by lazy { MainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewPager()
        loadData()
    }

    override fun setViewPagerData(marketData: Map<String, List<MarketResponse>>) {
        val titles = marketData.keys.toTypedArray()
        val marketNames = Array(titles.count()) { "" }

        for ((index: Int, value: String) in titles.withIndex()) {
            marketNames[index] = marketData
                .getValue(value)
                .joinToString { marketResponse -> marketResponse.market }
        }

        viewPagerAdapter.apply {
            setTitles(titles.toList())
            setData(marketNames)
        }
    }

    override fun onDestroy() {
        presenter.clearDisposable()
        super.onDestroy()
    }

    private fun initViewPager() {
        with(pager_content) {
            adapter = viewPagerAdapter
            layout_tab.setupWithViewPager(this)
            offscreenPageLimit = 3
        }
    }

    private fun loadData() {
        presenter.loadData()
    }

}

