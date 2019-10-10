package com.example.seonoh.seonohapp

import android.os.Bundle
import com.example.seonoh.seonohapp.contract.CoinMainContract
import com.example.seonoh.seonohapp.databinding.ActivityMainBinding
import com.example.seonoh.seonohapp.model.Market
import com.google.android.material.tabs.TabLayout

class MainActivity : BaseActivity<CoinMainContract.Presenter, ActivityMainBinding>(
    R.layout.activity_main
), CoinMainContract.View {

    override val presenter = CoinMainPresenter(this)
    private lateinit var coinMarketNameList: List<String>
    private lateinit var pagerAdapter: TabPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        presenter.loadData()
    }

    private fun initView() {
        pagerAdapter = TabPagerAdapter(supportFragmentManager)
        binding.run {
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
    }

    override fun setData(data: List<Market>) = pagerAdapter.setData(refineData(data))

    private fun refineData(marketData: List<Market>): List<String> {

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

}
