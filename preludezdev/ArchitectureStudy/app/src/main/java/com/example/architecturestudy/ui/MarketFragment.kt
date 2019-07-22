package com.example.architecturestudy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturestudy.data.CoinTickerResponse
import com.example.architecturestudy.network.NetworkHelper
import com.example.architecturestudy.ui.MainActivity
import com.example.architecturestudy.ui.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_market.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    fun loadData(markets: HashSet<String>) {
        rvAdapter.clearData()

        var marketList = markets.joinToString(separator = ",")

        //Ticker 정보들 가져오기
        NetworkHelper
            .coinApiService
            .getCurrTicker(marketList)
            .enqueue(object : Callback<List<CoinTickerResponse>> {
                override fun onFailure(call: Call<List<CoinTickerResponse>>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<List<CoinTickerResponse>>,
                    response: Response<List<CoinTickerResponse>>
                ) {
                    if (response.isSuccessful) {
                        var list = response.body()
                        for (ticker in list!!) {
                            rvAdapter.addData(ticker)
                        }

                        rvAdapter.notifyDataSetChanged()
                    }
                }
            })
    }
}