package kr.schoolsharing.coinhelper.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kr.schoolsharing.coinhelper.R
import kr.schoolsharing.coinhelper.data.Repository
import kr.schoolsharing.coinhelper.data.UpbitDataSource
import kr.schoolsharing.coinhelper.model.UpbitItem
import kr.schoolsharing.coinhelper.model.UpbitList
import kr.schoolsharing.coinhelper.model.UpbitMarket
import kr.schoolsharing.coinhelper.model.UpbitTicker
import kr.schoolsharing.coinhelper.util.MyPagerAdapter
import kr.schoolsharing.coinhelper.util.TextEditor


class MainActivity : AppCompatActivity() {

    private val repository: Repository = Repository()

    private lateinit var marketList: String
    private var upbitList = UpbitList(ArrayList(), ArrayList(), ArrayList(), ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        loadUpbitMarket()
        adaptPager()

    }

    private fun loadUpbitMarket() {
        repository.getMarket(object : UpbitDataSource.GetMarketCallback {
            override fun onMarketLoaded(markets: List<UpbitMarket>) {
                marketList = markets.joinToString(",") { it.market }
                loadUpbitTicker()
            }

            override fun onDataNotAvailable(t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun loadUpbitTicker() {
        repository.getTicker(marketList, object : UpbitDataSource.GetTickerCallback {

            override fun onTickerLoaded(tickers: List<UpbitTicker>) {
                for (item in tickers) {

                    val category = item.market.split("-")[0]
                    val name = item.market.split("-")[1]

                    val data = UpbitItem(
                        name,
                        TextEditor.makeTradePrice(item.tradePrice),
                        item.change,
                        TextEditor.makeSignedChangeRate(item.signedChangePrice),
                        TextEditor.makeAccTradePrice24h(item.accTradePrice24h)
                    )

                    when (category) {
                        "KRW" -> upbitList.krwList.add(data)
                        "BTC" -> upbitList.btcList.add(data)
                        "ETH" -> upbitList.ethList.add(data)
                        else -> upbitList.usdtList.add(data)
                    }
                }
            }


            override fun onDataNotAvailable(t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun adaptPager() {
        MyPagerAdapter(supportFragmentManager, upbitList).apply {
            viewpager_main.adapter = this
            tabs_main.setupWithViewPager(viewpager_main)
        }
    }

}
