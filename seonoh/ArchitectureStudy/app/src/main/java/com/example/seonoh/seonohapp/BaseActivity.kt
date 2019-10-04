package com.example.seonoh.seonohapp

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.seonoh.seonohapp.model.Market
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var pagerAdapter: TabPagerAdapter
    private lateinit var toast: Toast
    private lateinit var coinMarketNameList: List<String>

    fun initView() {
        pagerAdapter = TabPagerAdapter(supportFragmentManager)
        coinViewPager.run {
            adapter = pagerAdapter
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        }

        tabLayout.run {
            addTab(tabLayout.newTab().setText("KRW"))
            addTab(tabLayout.newTab().setText("BTC"))
            addTab(tabLayout.newTab().setText("ETH"))
            addTab(tabLayout.newTab().setText("USDT"))

            addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(coinViewPager))
        }
    }

    fun showToast() {
        if (toast.view.isShown) {
            finish()
        } else {
            toast.show()
        }
    }

    fun refineData(marketData: List<Market>): List<String> {

        coinMarketNameList = marketData.map {
            it.market.substringBefore("-")
        }.distinct()

        val marketDataList = ArrayList<String>()

        coinMarketNameList.forEach { title ->

            marketDataList += (marketData.filter {
                it.market.substringBefore("-") == title
            }.joinToString(",") {
                it.market
            })
        }

        return marketDataList
    }

    override fun onBackPressed() {
        showToast()
    }
}