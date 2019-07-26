package com.aiden.aiden.architecturepatternstudy.ui.main

import android.os.Bundle
import com.aiden.aiden.architecturepatternstudy.base.BaseActivity
import com.aiden.aiden.architecturepatternstudy.data.enums.Market
import com.aiden.aiden.architecturepatternstudy.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout


class MainActivity :
    BaseActivity<ActivityMainBinding>(com.aiden.aiden.architecturepatternstudy.R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            Market.values().forEach { market ->
                mainMarketTabTl.addTab(mainMarketTabTl.newTab().setText(market.marketName))
            }
            mainVp.adapter = MainTickerPagerAdapter(supportFragmentManager)
            mainVp.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(
                    mainMarketTabTl
                )
            )
            mainMarketTabTl.addOnTabSelectedListener(object : TabSelectedListener() {
                override fun onTabSelected(tl: TabLayout.Tab?) {
                    tl?.let { tab ->
                        mainVp.currentItem = tab.position
                    }
                }
            })
            supportFragmentManager.beginTransaction()
                .add(
                    com.aiden.aiden.architecturepatternstudy.R.id.main_fl,
                    MainSearchFragment()
                )
                .commit()
        }

    }

}
