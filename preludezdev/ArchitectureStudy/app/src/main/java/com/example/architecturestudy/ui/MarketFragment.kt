package com.example.architecturestudy

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturestudy.data.Coin
import com.example.architecturestudy.data.CoinMarketResponse
import com.example.architecturestudy.data.CoinTickerResponse
import com.example.architecturestudy.data.source.CoinsDataSource
import com.example.architecturestudy.data.source.CoinsRepository
import com.example.architecturestudy.data.source.local.CoinsLocalDataSource
import com.example.architecturestudy.data.source.remote.CoinsRemoteDataSource
import com.example.architecturestudy.ui.RecyclerViewAdapter
import com.example.architecturestudy.util.Util
import kotlinx.android.synthetic.main.fragment_market.*

class MarketFragment : Fragment() {

    private val repository = CoinsRepository.getInstance(CoinsRemoteDataSource, CoinsLocalDataSource)
    private val rvAdapter = RecyclerViewAdapter()

    companion object {
        fun newInstance(market: String): MarketFragment {
            val fragment = MarketFragment()
            val args = Bundle()

            when (market) {
                "KRW" -> args.putString("KEY_MARKET", "KRW")
                "BTC" -> args.putString("KEY_MARKET", "BTC")
                "ETH" -> args.putString("KEY_MARKET", "ETH")
                "USDT" -> args.putString("KEY_MARKET", "USDT")
            }

            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_market, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //리사이클러뷰 어댑터와 레이아웃매니저 설정
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = rvAdapter
        }

        var keyMarket = arguments?.get("KEY_MARKET") as String

        loadData(keyMarket) // 해당 마켓의 데이터 불러오기
    }

    private fun loadData(keyMarket: String) {
        rvAdapter.clearData()

        repository
            .getAllMarket(object : CoinsDataSource.GetAllMarketCallback {
                override fun onAllMarketLoaded(markets: List<CoinMarketResponse>) {
                    val marketList = mutableListOf<String>()
                    markets.forEach {
                        val currMarket = it.market
                        if (currMarket.split('-')[0] == keyMarket) {
                            marketList.add(currMarket)
                        }
                    }

                    val targetTickers = marketList.joinToString(separator = ",")

                    repository
                        .getCoinTickers(targetTickers, object : CoinsDataSource.GetCoinTickersCallback {
                            override fun onTickersLoaded(tickers: List<CoinTickerResponse>) {

                                //map() 스트림 함수 : 컬렉션 내 인자를 변환하여 반환
                                //리싸이클러뷰 어댑터에 데이터 장착
                                rvAdapter.setData(tickers.map {
                                    convertTickerIntoCoin(it)
                                })
                            }

                            override fun onDataNotAvailable() {
                                Log.d("test", "All Market Data Not Available")
                            }
                        })
                }

                override fun onDataNotAvailable() {
                    Log.d("test", "getAllMarket data not available")
                }
            })
    }

    private fun convertTickerIntoCoin(ticker: CoinTickerResponse): Coin {
        //코인 이름
        var market = ticker.market.split("-")[1]

        //현재가
        var tradePrice = when {
            ticker.tradePrice > 1_000 ->
                Util.convertBigNumberToStdString(ticker.tradePrice.toInt())
            ticker.tradePrice > 2 ->
                String.format("%.2f", ticker.tradePrice)
            else ->
                String.format("%.8f", ticker.tradePrice)
        }

        //전일대비
        var signedChangeRate = String.format("%.2f", ticker.signedChangeRate * 100) + "%"

        //전일대비 색깔 지정
        val coinColor = if (signedChangeRate.startsWith('-')) Color.BLUE else Color.RED

        //거래대금
        var accTradePriceH = when {
            ticker.accTradePriceH > 10_000_000 -> {
                Util.convertBigNumberToStdString((ticker.accTradePriceH / 1000000).toInt()) + "M"
            }

            ticker.accTradePriceH > 100_000 ->
                Util.convertBigNumberToStdString(ticker.accTradePriceH.toInt() / 1000) + "k"

            ticker.accTradePriceH > 1_000 ->
                Util.convertBigNumberToStdString(ticker.accTradePriceH.toInt())

            else ->
                String.format("%.3f", ticker.accTradePriceH)
        }

        return Coin(market, tradePrice, signedChangeRate, accTradePriceH, coinColor)
    }

}