package com.example.seonoh.seonohapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.seonoh.seonohapp.model.Market
import com.example.seonoh.seonohapp.network.CoinRequest
import com.example.seonoh.seonohapp.network.RetrofitCreator
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CoinRequest.MarketResultListener {

    private lateinit var mPagerAdapter: TabPagerAdapter
    private val TAG = "COIN_MAIN"
    private lateinit var toast: Toast
    private lateinit var conMarketNameList: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toast = Toast.makeText(this, "뒤로가기를 한번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT)
        CoinRequest().marketSend(RetrofitCreator.coinApi, this@MainActivity)
    }

    fun initView(data: ArrayList<String>) {
        mPagerAdapter = TabPagerAdapter(supportFragmentManager, data, conMarketNameList.size)
        coinViewPager.adapter = mPagerAdapter

        coinViewPager.addOnPageChangeListener(object : TabLayout.TabLayoutOnPageChangeListener(tabLayout) {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                when (position) {
                    0 -> {
                        Log.e(TAG, "KRW")
                    }

                    1 -> {
                        Log.e(TAG, "BTC")

                    }

                    2 -> {
                        Log.e(TAG, "ETH")

                    }
                    3 -> {
                        Log.e(TAG, "USDT")

                    }
                }
            }
        })

        tabLayout.apply {

            conMarketNameList.forEach {
                addTab(tabLayout.newTab().setText(it))
            }

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

    override fun getMarketSuccess(result: ArrayList<Market>) {
        val data = classifyMarketData(result)
        initView(data)
    }

    override fun getMarketFailed(code: String) {
        Log.e("marketfailed","getMarketFailed code : $code")
    }

}
