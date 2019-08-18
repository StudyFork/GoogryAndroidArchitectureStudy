package com.example.architecturestudy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.architecturestudy.R
import com.example.architecturestudy.model.MarketResponse
import com.example.architecturestudy.ui.adapter.ViewPagerAdapter
import com.example.architecturestudy.network.UpbitListener
import com.example.architecturestudy.network.UpbitRequest
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getInformation()
    }

    private fun getInformation() {

        UpbitRequest()
            .getMarketInfo(object : UpbitListener<MarketResponse> {
                override fun onResponse(dataList: List<MarketResponse>) {

                    currency_tab_layout.run {
                        pageList.forEach {
                            addTab(newTab().setText(getString(it)))
                        }

                        addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                            override fun onTabReselected(p0: TabLayout.Tab) {
                                coin_list_viewpager.currentItem = p0.position
                            }

                            override fun onTabUnselected(p0: TabLayout.Tab) {
                            }

                            override fun onTabSelected(p0: TabLayout.Tab) {

                            }

                        })
                    }

                    coin_list_viewpager.run {
                        adapter = ViewPagerAdapter(supportFragmentManager, this@MainActivity, pageList, dataList)
                        addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(currency_tab_layout))
                    }
                }

                override fun onFailure(str: String) {
                }

            })
    }


    companion object {
        val pageList = listOf(
            R.string.currency_1,
            R.string.currency_2,
            R.string.currency_3,
            R.string.currency_4
        )
    }
}
