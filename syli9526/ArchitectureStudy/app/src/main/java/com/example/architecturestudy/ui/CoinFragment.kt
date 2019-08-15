package com.example.architecturestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturestudy.R
import com.example.architecturestudy.model.TickerResponse
import com.example.architecturestudy.ui.adapter.CoinAdapter
import com.example.architecturestudy.util.UpbitListener
import com.example.architecturestudy.util.UpbitRequest
import kotlinx.android.synthetic.main.fragment_list_coin.*
import java.text.DecimalFormat

class CoinFragment : Fragment(), CoinAdapter.CoinItemRecyclerViewClickListener {

    var currencyList: List<String>? = null
    lateinit var coinAdapter: CoinAdapter

    override fun onItemClicked(position: Int) {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return return inflater.inflate(R.layout.fragment_list_coin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        currencyList = arguments?.getStringArrayList(CURRENCY_LIST)
        currencyList?.let {
            getTickerInfo(it.joinToString(","))
        }

        coinAdapter = CoinAdapter(requireContext(), this)

        rv_coin_list.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = coinAdapter
        }

    }

    private fun getTickerInfo(name: String) {

        UpbitRequest().apply {
            getTickerInfo(object : UpbitListener<TickerResponse> {

                override fun onResponse(dataList: List<TickerResponse>) {

                    var list = mutableListOf<CoinInfo>()
                    dataList.forEach {
                        val cur = it.market.split("-")[0]
                        val coin = it.market.split("-")[1]
                        val price = DecimalFormat("0.########").format(it.tradePrice)
                        val compare: String
                        val compareColor: Int

                        if (it.tradePrice < it.prevClosingPrice) {
                            compare =
                                "-" + DecimalFormat("0.##").format((1 - (it.tradePrice / it.prevClosingPrice)) * 10.0) + "%"
                            compareColor = R.color.colorRed
                        } else {
                            compare =
                                DecimalFormat("0.##").format((1 - (it.prevClosingPrice / it.tradePrice)) * 10.0) + "%"
                            compareColor = R.color.colorBlue
                        }
                        val amount: String
                        when (cur) {

                            getString(R.string.currency_1) -> {
                                amount = String.format("%,d", (it.accTradePrice24h / 1000000).toInt()) + "M"
                            }
                            getString(R.string.currency_2) -> {
                                amount = String.format(
                                    "%,d", DecimalFormat("0.###").format(it.accTradePrice24h).split(".")[0].toInt()
                                ) + "." + DecimalFormat("0.###").format(it.accTradePrice24h).split(".")[1]
                            }
                            getString(R.string.currency_3) -> {
                                amount = String.format(
                                    "%,d", DecimalFormat("0.###").format(it.accTradePrice24h).split(".")[0].toInt()
                                ) + "." + DecimalFormat("0.###").format(it.accTradePrice24h).split(".")[1]
                            }
                            getString(R.string.currency_4) -> {
                                amount = String.format(
                                    "%,d",
                                    DecimalFormat("0.###").format(it.accTradePrice24h / 1000).split(".")[0].toInt()
                                ) + " k"
                            }
                            else -> amount = ""
                        }

                        list.add(CoinInfo(cur, coin, price, compare, compareColor, amount))
                    }

                    coinAdapter.setData(list)
                }

                override fun onFailure(str: String) {

                }

            }, name)
        }
    }

    companion object {

        private const val CURRENCY_LIST = "currency_list"

        fun newInstance(currencyList: ArrayList<String>) = CoinFragment().apply {

            arguments = Bundle().apply {
                putStringArrayList(CURRENCY_LIST, currencyList)
            }
        }
    }


}