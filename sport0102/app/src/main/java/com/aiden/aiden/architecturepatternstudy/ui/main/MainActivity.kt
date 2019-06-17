package com.aiden.aiden.architecturepatternstudy.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.aiden.aiden.architecturepatternstudy.data.enums.Market
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_main_tab.view.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.aiden.aiden.architecturepatternstudy.R.layout.activity_main)
        Market.values().forEach {
            main_market_tab_tl.addTab(main_market_tab_tl.newTab().setCustomView(createTabView(it.marketName)))
        }
        main_vp.adapter = MainPagerAdapter(supportFragmentManager)
        main_vp.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(main_market_tab_tl))
        main_market_tab_tl.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    main_vp.currentItem = it.position
                }
            }

        })
    }

    private fun createTabView(tabName: String): View {
        val tabView =
            LayoutInflater.from(this).inflate(com.aiden.aiden.architecturepatternstudy.R.layout.item_main_tab, null)
        tabView.item_main_tab_tv.text = tabName
        return tabView
    }
}
