package com.example.architecturestudy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.architecturestudy.R
import com.example.architecturestudy.data.source.CoinRepository
import com.example.architecturestudy.network.model.MarketResponse
import com.example.architecturestudy.ui.adapter.ViewPagerAdapter
import com.example.architecturestudy.data.source.UpbitListener
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewPagerAdapter by lazy { ViewPagerAdapter(supportFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setPage()
        getInformation()
    }

    private fun getInformation() {

        CoinRepository.getInstance().getMarketInfo(object : UpbitListener<MarketResponse> {
            override fun onResponse(dataList: List<MarketResponse>) {
                viewPagerAdapter.setData(pageList, dataList)
            }

            override fun onFailure(str: String) {
                Toast.makeText(this@MainActivity, "Call Failure : $str", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun setPage() {

        currency_tab_layout.run {
            pageList.forEach {
                addTab(newTab().setText(it))
            }

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(p0: TabLayout.Tab) {
                    coin_list_viewpager.currentItem = p0.position
                }

                override fun onTabUnselected(p0: TabLayout.Tab) {
                }

                override fun onTabSelected(p0: TabLayout.Tab) {
                    coin_list_viewpager.currentItem = p0.position
                }

            })
        }
        coin_list_viewpager.run {
            adapter = viewPagerAdapter
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(currency_tab_layout))
        }
    }


    companion object {
        val pageList = listOf(
            "KRW",
            "BTC",
            "ETH",
            "USDT"
        )
    }
}
