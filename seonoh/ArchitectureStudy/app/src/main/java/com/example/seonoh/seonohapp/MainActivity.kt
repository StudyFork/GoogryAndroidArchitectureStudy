package com.example.seonoh.seonohapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.seonoh.seonohapp.model.Market
import com.example.seonoh.seonohapp.network.CoinRequest
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CoinRequest.BaseResult<ArrayList<Market>> {

    private lateinit var pagerAdapter: TabPagerAdapter
    private lateinit var toast: Toast
    private lateinit var conMarketNameList: List<String>
    private val coinRepository = CoinRepositoryImpl()

    override fun getNetworkSuccess(result: ArrayList<Market>) {
        val data = classifyMarketData(result)
        pagerAdapter.setData(data)
    }

    override fun getNetworkFailed(code: String) {
        Log.e("marketfailed", "getMarketFailed code : $code")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        toast = Toast.makeText(this, "뒤로가기를 한번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT)
        coinRepository.sendMarket(this)
    }

    fun initView() {
        pagerAdapter = TabPagerAdapter(supportFragmentManager)
        coinViewPager.apply {
            adapter = pagerAdapter
            addOnPageChangeListener(object : TabLayout.TabLayoutOnPageChangeListener(tabLayout) {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }
            })
        }

        tabLayout.apply {
            addTab(tabLayout.newTab().setText("KRW"))
            addTab(tabLayout.newTab().setText("BTC"))
            addTab(tabLayout.newTab().setText("ETH"))
            addTab(tabLayout.newTab().setText("USDT"))

            addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(coinViewPager))
        }
    }

    override fun onBackPressed() {
        if (toast.view.isShown) {
            finish()
        } else {
            toast.show()
        }
    }

    private fun classifyMarketData(marketData: ArrayList<Market>): ArrayList<String> {

        conMarketNameList = marketData.map {
            it.market.substringBefore("-")
        }.distinct()

        val marketDataList = ArrayList<String>()

        conMarketNameList.forEach { title ->

            marketDataList += (marketData.filter {
                it.market.substringBefore("-") == title
            }.joinToString(",") {
                it.market
            })
        }

        return marketDataList
    }


}
