package com.example.architecturestudy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.architecturestudy.R
import com.example.architecturestudy.network.model.MarketResponse
import com.example.architecturestudy.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private val viewPagerAdapter by lazy { ViewPagerAdapter(supportFragmentManager) }
    private val presenter by lazy { MainPresenter(this) }
    private val pageList = listOf(
        "KRW",
        "BTC",
        "ETH",
        "USDT"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setPage()
        presenter.getMarketList()
    }

    override fun setData(marketList: List<MarketResponse>) {
        viewPagerAdapter.setData(pageList, marketList)
    }

    override fun setPage() {

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

    override fun showMessage(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
    }

}
