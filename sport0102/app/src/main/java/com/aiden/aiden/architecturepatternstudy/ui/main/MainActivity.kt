package com.aiden.aiden.architecturepatternstudy.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aiden.aiden.architecturepatternstudy.data.enums.Market
import com.aiden.aiden.architecturepatternstudy.data.source.UpbitRepository
import com.aiden.aiden.architecturepatternstudy.data.source.remote.UpbitRemoteDataSource
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_main_tab.view.*


class MainActivity : AppCompatActivity() {

    private lateinit var krwPresenter: MainPresenter
    private lateinit var btcPresenter: MainPresenter
    private lateinit var ethPresenter: MainPresenter
    private lateinit var usdtPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.aiden.aiden.architecturepatternstudy.R.layout.activity_main)
        Market.values().forEach {
            main_market_tab_tl.addTab(main_market_tab_tl.newTab().setCustomView(createTabView(it.marketName)))
        }
        val krwFragment = MainFragment(Market.KRW.marketName)
        val btcFragment = MainFragment(Market.BTC.marketName)
        val ethFragment = MainFragment(Market.ETH.marketName)
        val usdtFragment = MainFragment(Market.USDT.marketName)
        val fragmentList = ArrayList<Fragment>().apply {
            this.add(krwFragment)
            this.add(btcFragment)
            this.add(ethFragment)
            this.add(usdtFragment)
        }
        krwPresenter = MainPresenter(
            UpbitRepository.getInstance(UpbitRemoteDataSource), krwFragment
        )
        btcPresenter = MainPresenter(
            UpbitRepository.getInstance(UpbitRemoteDataSource), btcFragment
        )
        ethPresenter = MainPresenter(
            UpbitRepository.getInstance(UpbitRemoteDataSource), ethFragment
        )
        usdtPresenter = MainPresenter(
            UpbitRepository.getInstance(UpbitRemoteDataSource), usdtFragment
        )

        main_vp.adapter = MainPagerAdapter(supportFragmentManager, fragmentList)
        main_vp.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(main_market_tab_tl))
    }

    private fun createTabView(tabName: String): View {
        val tabView =
            LayoutInflater.from(this).inflate(com.aiden.aiden.architecturepatternstudy.R.layout.item_main_tab, null)
        tabView.item_main_tab_tv.text = tabName
        return tabView
    }
}
