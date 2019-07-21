package com.architecture.study.fragment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.architecture.study.R
import com.architecture.study.adapter.CoinListAdapter
import com.architecture.study.item.Ticker
import com.architecture.study.model.TickerResponse
import com.architecture.study.server.UpbitRequest
import com.architecture.study.server.UpbitRequestListener
import kotlinx.android.synthetic.main.fragment_coinlist.*
import java.text.DecimalFormat

class CoinListFragment : Fragment(), CoinListAdapter.CoinItemRecyclerViewClickListener {
    private lateinit var coinListAdapter: CoinListAdapter

    private var monetaryUnitNameList: List<String>? = null
    private lateinit var tickerList: List<Ticker>

    private var refreshHandler = Handler()


    /* 현재 보고있는 화면의 데이터만 갱신, 5초 간격 */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            refreshData()
        } else {
            refreshHandler.removeMessages(0)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_coinlist, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        /* 받아온 argument - Coin name */
        monetaryUnitNameList = arguments?.getStringArrayList(MONETARY_UNIT_NAME_LIST)

        monetaryUnitNameList?.let {
            getTickerList(it.joinToString(","))
        }


        coinListAdapter = CoinListAdapter(requireContext(), this)

        coin_list_recyclerview.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = coinListAdapter
        }
    }

    /* handler로 5초간격 호출 재귀함수 */
    private fun refreshData() {
        refreshHandler = Handler().apply {
            postDelayed({
                monetaryUnitNameList?.let {
                    getTickerList(it.joinToString(","))
                }
            }, 5000)
        }
    }


    /* retrofit getTickerList */
    private fun getTickerList(marketNames: String) {
        UpbitRequest().apply {
            getTickerList(marketNames, object : UpbitRequestListener<TickerResponse> {
                override fun onSucess(dataList: List<TickerResponse>) {

                    val _tickerList = mutableListOf<Ticker>()
                    dataList.forEach {
                        val unitName = it.market.split("-")[0]
                        val coinName = it.market.split("-")[1]
                        val nowPrice = DecimalFormat("0.########").format(it.tradePrice)
                        val compareYesterday: String
                        val compareYesterdayTextColor: Int
                        if (it.tradePrice > it.prevClosingPrice) {
                            compareYesterday =
                                DecimalFormat("0.##").format((1 - (it.prevClosingPrice / it.tradePrice)) * 10.0) + "%"
                            compareYesterdayTextColor = R.color.colorRed
                        } else {
                            compareYesterday =
                                "-" + DecimalFormat("0.##").format((1 - (it.tradePrice / it.prevClosingPrice)) * 10.0) + "%"
                            compareYesterdayTextColor = R.color.colorBlue
                        }
                        val transactionAmount = when (it.market.split("-")[0]) {
                            getString(R.string.monetary_unit_1) -> {
                                String.format("%,d", (it.accTradePrice24h / 1000000).toInt()) + "M"
                            }
                            getString(R.string.monetary_unit_2) -> {
                                String.format(
                                    "%,d",
                                    DecimalFormat("0.###").format(it.accTradePrice24h).split(".")[0].toInt()
                                ) +
                                        "." + DecimalFormat("0.###").format(it.accTradePrice24h).split(".")[1]
                            }
                            getString(R.string.monetary_unit_3) -> {
                                String.format(
                                    "%,d",
                                    DecimalFormat("0.###").format(it.accTradePrice24h).split(".")[0].toInt()
                                ) +
                                        "." + DecimalFormat("0.###").format(it.accTradePrice24h).split(".")[1]
                            }
                            getString(R.string.monetary_unit_4) -> {
                                String.format(
                                    "%,d",
                                    DecimalFormat("0.###").format(it.accTradePrice24h / 1000).split(".")[0].toInt()
                                ) + " k"
                            }
                            else -> error("error")
                        }
                        _tickerList.add(
                            Ticker(
                                unitName,
                                coinName,
                                nowPrice,
                                compareYesterday,
                                compareYesterdayTextColor,
                                transactionAmount
                            ).apply {
                                Log.d("ddd", toString())
                            }
                        )
                    }
                    tickerList = _tickerList
                    coinListAdapter.setData(tickerList)
                }

                override fun onEmpty(str: String) {

                }

                override fun onFailure(str: String) {

                }
            })
        }
    }

    override fun onItemClicked(position: Int) {
        //click event
    }

    /* fragment singleton */
    companion object {
        fun newInstance(monetaryUnitNameList: ArrayList<String>) = CoinListFragment().apply {
            arguments = Bundle().apply {
                putStringArrayList(MONETARY_UNIT_NAME_LIST, monetaryUnitNameList)
            }
        }

        private const val MONETARY_UNIT_NAME_LIST = "MONETARY_UNIT_NAME"
    }
}