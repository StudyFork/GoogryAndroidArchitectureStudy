package com.example.architecturestudy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturestudy.ui.MainActivity
import com.example.architecturestudy.ui.RecyclerViewAdapter
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
            "KRW" -> rvAdapter.loadData(MainActivity.KRW_MARKETS)
            "BTC" -> rvAdapter.loadData(MainActivity.BTC_MARKETS)
            "ETH" -> rvAdapter.loadData(MainActivity.ETH_MARKETS)
            "USDT" -> rvAdapter.loadData(MainActivity.USDT_MARKETS)
        }
    }
}