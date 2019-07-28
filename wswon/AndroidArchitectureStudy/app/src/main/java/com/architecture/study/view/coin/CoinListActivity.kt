package com.architecture.study.view.coin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.architecture.study.R
import com.architecture.study.data.repository.CoinRepositoryImp
import com.architecture.study.view.coin.adapter.CoinTabPagerAdapter
import com.architecture.study.network.model.MarketResponse
import com.architecture.study.data.source.CoinRemoteDataSourceListener
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class CoinListActivity : AppCompatActivity() {

    private lateinit var marketList: List<MarketResponse>

    private val tabList = listOf(
        R.string.monetary_unit_1,
        R.string.monetary_unit_2,
        R.string.monetary_unit_3,
        R.string.monetary_unit_4
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getMarketList()
    }

    private fun getMarketList() {
        CoinRepositoryImp.getInstance().getMarketList(object :
            CoinRemoteDataSourceListener<MarketResponse> {
            override fun onSucess(dataList: List<MarketResponse>) {
                marketList = dataList
                setPager()
            }

            override fun onEmpty(str: String) {
                Toast.makeText(this@CoinListActivity, "data Empty : $str", Toast.LENGTH_LONG).show()

            }

            override fun onFailure(str: String) {
                Toast.makeText(this@CoinListActivity, "call failure : $str", Toast.LENGTH_LONG).show()
            }
        })
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
            adapter = CoinTabPagerAdapter(
                supportFragmentManager,
                tabList, this@CoinListActivity, marketList
            )
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(monetary_unit_tablayout))
        }

    }

}
