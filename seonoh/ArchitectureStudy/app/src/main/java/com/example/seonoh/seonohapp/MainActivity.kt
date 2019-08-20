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

    companion object {
        @JvmStatic
        var krwMarketData: String = ""

        @JvmStatic
        var btcMarketData: String = ""

        @JvmStatic
        var ethMarketData: String = ""

        @JvmStatic
        var usdtMarketData: String = ""
    }

    lateinit var mPagerAdapter: TabPagerAdapter
    private val TAG = "COIN_MAIN"
    private val TAB_COUNT = 4
    lateinit var toast: Toast
    lateinit var conMarketNameList: List<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toast = Toast.makeText(this, "뒤로가기를 한번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT)

        CoinRequest().marketSend(RetrofitCreator.coinApi,this@MainActivity)

    }

    fun initView() {
        mPagerAdapter = TabPagerAdapter(supportFragmentManager, conMarketNameList.size)
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

    private fun classifyMarketData(marketData: ArrayList<Market>) {

        conMarketNameList = marketData.map {
            it.market.substringBefore("-")
        }.distinct()

        val marketDataList = ArrayList<String>()

        conMarketNameList.forEach { title ->
            marketDataList += marketData.filter {
                it.market.substringBefore("-") == title
            }.joinToString(",") {
                it.market
            }
        }

        for (i in 0 until marketDataList.size) {

            when (marketDataList[i].substring(0, 3)) {
                "KRW" -> krwMarketData += marketDataList[i] + ","
                "BTC" -> btcMarketData += marketDataList[i] + ","
                "ETH" -> ethMarketData += marketDataList[i] + ","
                "USD" -> usdtMarketData += marketDataList[i] + ","
            }

        }

        krwMarketData = krwMarketData.substring(0, krwMarketData.length - 1)
        btcMarketData = btcMarketData.substring(0, btcMarketData.length - 1)
        ethMarketData = ethMarketData.substring(0, ethMarketData.length - 1)
        usdtMarketData = usdtMarketData.substring(0, usdtMarketData.length - 1)
    }

    override fun getMarketSuccess(result: ArrayList<Market>) {

        classifyMarketData(result)
        initView()
    }

    override fun getMarketFailed(code: String) {
    }

}
