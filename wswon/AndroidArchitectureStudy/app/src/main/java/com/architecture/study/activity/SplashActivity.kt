package com.architecture.study.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.architecture.study.R
import com.architecture.study.model.Market
import com.architecture.study.model.Ticker
import com.architecture.study.server.UpbitRequest

class SplashActivity : AppCompatActivity() {
    /*
    * 초기데이터 저장 & splash activity
    * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        getMarketList()
    }


    /* retrofit getMarketList 초기에 1회만 호출
    *  getTickerList의 Query역할
    * */
    private fun getMarketList() {
        UpbitRequest().apply {
            getMarketList()
            setOnUpbitRequestListener(object : UpbitRequest.UpbitRequestListener {
                override fun onSucess(marketList: List<Any>) {
                    SplashActivity.marketList = marketList as List<Market>
                    var marketNames = ""
                    for (market in SplashActivity.marketList) {
                        marketNames += if (marketList.lastIndex == marketList.indexOf(market)) {
                            "${market.market}"
                        } else {
                            "${market.market},"
                        }
                    }
                    this@SplashActivity.getTickerList(marketNames)
                }

                override fun onEmpty(str: String) {

                }

                override fun onFailure(str: String) {

                }
            })
        }
    }

    private fun getTickerList(marketNames: String) {
        UpbitRequest().apply {
            getTickerList(marketNames)
            setOnUpbitRequestListener(object : UpbitRequest.UpbitRequestListener {
                override fun onSucess(tickerList: List<Any>) {
                    SplashActivity.tickerList = tickerList as List<Ticker>
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                }

                override fun onEmpty(str: String) {
                }

                override fun onFailure(str: String) {

                }
            })
        }
    }

    companion object {
        lateinit var marketList: List<Market>
        lateinit var tickerList: List<Ticker>
    }
}
