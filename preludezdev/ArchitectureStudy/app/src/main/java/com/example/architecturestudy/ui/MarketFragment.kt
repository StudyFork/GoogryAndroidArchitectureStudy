package com.example.architecturestudy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturestudy.data.Coin
import com.example.architecturestudy.data.CoinTickerResponse
import com.example.architecturestudy.data.source.CoinsDataSource
import com.example.architecturestudy.data.source.CoinsRepository
import com.example.architecturestudy.data.source.local.CoinsLocalDataSource
import com.example.architecturestudy.data.source.remote.CoinsRemoteDataSource
import com.example.architecturestudy.ui.MainActivity
import com.example.architecturestudy.ui.RecyclerViewAdapter
import com.example.architecturestudy.util.Util
import kotlinx.android.synthetic.main.fragment_market.*

class MarketFragment : Fragment() {

    private val rvAdapter = RecyclerViewAdapter()

    companion object {
        fun newInstance(market: String): MarketFragment {
            val fragment = MarketFragment()
            val args = Bundle()

            when (market) {
                "KRW" -> args.putSerializable("type", "KRW")
                "BTC" -> args.putSerializable("type", "BTC")
                "ETH" -> args.putSerializable("type", "ETH")
                "USDT" -> args.putSerializable("type", "USDT")
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
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = rvAdapter

        //마켓별로 다르게 데이터 불러오기
        var type = arguments?.get("type")

        when (type) {
            "KRW" -> loadData(MainActivity.KRW_MARKETS)
            "BTC" -> loadData(MainActivity.BTC_MARKETS)
            "ETH" -> loadData(MainActivity.ETH_MARKETS)
            "USDT" -> loadData(MainActivity.USDT_MARKETS)
        }
    }

    private fun loadData(marketList: List<String>) {
        rvAdapter.clearData()

        //Tickers 정보 가져오기
        CoinsRepository
            .getInstance(CoinsRemoteDataSource, CoinsLocalDataSource)
            .getCoinTickers(marketList, object : CoinsDataSource.GetCoinTickersCallback {
                override fun onTickersLoaded(tickers: List<CoinTickerResponse>) {
                    val list = mutableListOf<Coin>()

                    for (ticker in tickers) {
                        //ticker 를 Coin 클래스로 변환해서 저장
                        list.add(convertTickerIntoCoin(ticker))
                    }

                    rvAdapter.setData(list)
                }

                override fun onDataNotAvailable() {
                    Log.d("test", "All Market Data Not Available")
                }
            })

    }

    fun convertTickerIntoCoin(ticker: CoinTickerResponse): Coin {
        //코인 이름
        var market = ticker.market.split("-")[1]

        //현재가
        var tradePrice = when {
            ticker.tradePrice > 1000 ->
                Util.convertBigNumberToStdString(ticker.tradePrice.toInt())
            ticker.tradePrice > 2 ->
                String.format("%.2f", ticker.tradePrice)
            else ->
                String.format("%.8f", ticker.tradePrice)
        }

        //전일대비
        var signedChangeRate = String.format("%.2f", ticker.signedChangeRate * 100) + "%"

        //거래대금
        var accTradePriceH = when {
            ticker.accTradePriceH > 10000000 -> {
                Util.convertBigNumberToStdString((ticker.accTradePriceH / 1000000).toInt()) + "M"
            }

            ticker.accTradePriceH > 100000 ->
                Util.convertBigNumberToStdString(ticker.accTradePriceH.toInt() / 1000) + "k"

            ticker.accTradePriceH > 1000 ->
                Util.convertBigNumberToStdString(ticker.accTradePriceH.toInt())

            else ->
                String.format("%.3f", ticker.accTradePriceH)
        }

        return Coin(market, tradePrice, signedChangeRate, accTradePriceH)
    }

}