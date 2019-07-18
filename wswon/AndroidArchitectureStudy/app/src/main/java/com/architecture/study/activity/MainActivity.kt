package com.architecture.study.activity

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.architecture.study.R
import com.architecture.study.adapter.TabPagerAdapter
import com.architecture.study.model.Market
import com.architecture.study.model.Ticker
import com.architecture.study.server.UpbitRequest
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var marketList: List<Market>
    lateinit var tickerList: List<Ticker>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        marketList = SplashActivity.marketList
        tickerList = SplashActivity.tickerList
        setPager()
    }

    /* 데이터 갱신역할을 하는 함수 CoinListFragment에서 Call*/
    fun refreshTickerData() {
        if (marketList.isNotEmpty()) {
            var marketNames = ""
            for (market in marketList) {
                marketNames += if (marketList.lastIndex == marketList.indexOf(market)) {
                    "${market.market}"
                } else {
                    "${market.market},"
                }
            }
            Handler().postDelayed({
                getTickerList(marketNames)
                refreshTickerData()
            }, 5000)
        } else {
            Handler().postDelayed({
                refreshTickerData()
            }, 5000)
        }

    }

    /* retrofit getTickerList */
    private fun getTickerList(marketNames: String) {
        UpbitRequest().apply {
            getTickerList(marketNames)
            setOnUpbitRequestListener(object : UpbitRequest.UpbitRequestListener {
                override fun onSucess(tickerList: List<Any>) {
                    this@MainActivity.tickerList = tickerList as List<Ticker>
                }

                override fun onEmpty(str: String) {
                }

                override fun onFailure(str: String) {

                }
            })
        }
    }

    /* tab layout && view page init*/
    private fun setPager() {
        monetary_unit_tablayout.run {
            addTab(newTab().setText(getString(R.string.monetary_unit_1)))
            addTab(newTab().setText(getString(R.string.monetary_unit_2)))
            addTab(newTab().setText(getString(R.string.monetary_unit_3)))
            addTab(newTab().setText(getString(R.string.monetary_unit_4)))

            tabGravity = TabLayout.GRAVITY_FILL

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    Log.d("presse", "tab.position")
                    coin_list_viewpager.currentItem = tab.position
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }
            })
        }

        coin_list_viewpager.run {
            adapter = TabPagerAdapter(supportFragmentManager, monetary_unit_tablayout.tabCount, this@MainActivity)
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(monetary_unit_tablayout))
//            offscreenPageLimit = 4
        }

    }



}
