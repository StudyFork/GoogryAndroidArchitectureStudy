package com.architecture.study.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.architecture.study.R
import com.architecture.study.adapter.TabPagerAdapter
import com.architecture.study.model.MarketResponse
import com.architecture.study.server.UpbitRequest
import com.architecture.study.server.UpbitRequestListener
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var marketList: List<MarketResponse>

    private var initTabLayout = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getMarketList()
    }

    private fun getMarketList() {
        UpbitRequest().apply {
            getMarketList(object : UpbitRequestListener<MarketResponse> {
                override fun onSucess(dataList: List<MarketResponse>) {
                    marketList = dataList
                    if (!initTabLayout) {
                        initTabLayout = true
                        setPager()
                    }
                }

                override fun onEmpty(str: String) {

                }

                override fun onFailure(str: String) {

                }
            })
        }
    }

    /* tab layout && view pager init*/
    private fun setPager() {
        monetary_unit_tablayout.run {
            tabList.forEach {
                addTab(newTab().setText(getString(it)))
            }
            tabGravity = TabLayout.GRAVITY_FILL

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    coin_list_viewpager.currentItem = tab.position

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }
            })
        }

        coin_list_viewpager.run {
            adapter = TabPagerAdapter(supportFragmentManager, tabList, this@MainActivity, marketList)
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(monetary_unit_tablayout))
        }

    }

    companion object {
        val tabList = listOf(
            R.string.monetary_unit_1,
            R.string.monetary_unit_2,
            R.string.monetary_unit_3,
            R.string.monetary_unit_4
        )
    }
}
