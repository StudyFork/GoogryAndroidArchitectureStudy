package com.aiden.aiden.architecturepatternstudy.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.aiden.aiden.architecturepatternstudy.R
import com.aiden.aiden.architecturepatternstudy.base.BaseActivity
import com.aiden.aiden.architecturepatternstudy.data.enums.Market
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_main_tab.view.*


class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Market.values().forEach {
            main_market_tab_tl.addTab(main_market_tab_tl.newTab().setCustomView(createTabView(it.marketName)))
        }
        main_vp.adapter = MainPagerAdapter(supportFragmentManager)
        main_vp.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(main_market_tab_tl))
        main_market_tab_tl.addOnTabSelectedListener(object : TabSelectedListener() {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    main_vp.currentItem = it.position
                }
            }
        })
    }

    private fun createTabView(tabName: String): View =
        LayoutInflater.from(this).inflate(R.layout.item_main_tab, null)
            .apply {
                this.item_main_tab_tv.text = tabName
            }
}
