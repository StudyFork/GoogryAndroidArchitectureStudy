package com.aiden.aiden.architecturepatternstudy.ui.main

import android.os.Bundle
import com.aiden.aiden.architecturepatternstudy.R
import com.aiden.aiden.architecturepatternstudy.base.BaseActivity
import com.aiden.aiden.architecturepatternstudy.data.enums.Market
import com.aiden.aiden.architecturepatternstudy.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            Market.values().forEach { market ->
                it.mainMarketTabTl.addTab(it.mainMarketTabTl.newTab().setText(market.marketName))
            }
            it.mainVp.adapter = MainPagerAdapter(supportFragmentManager)
            it.mainVp.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(
                    it.mainMarketTabTl
                )
            )
            it.mainMarketTabTl.addOnTabSelectedListener(object : TabSelectedListener() {
                override fun onTabSelected(tl: TabLayout.Tab?) {
                    tl?.let { tab ->
                        it.mainVp.currentItem = tab.position
                    }
                }
            })
        }

    }

}
