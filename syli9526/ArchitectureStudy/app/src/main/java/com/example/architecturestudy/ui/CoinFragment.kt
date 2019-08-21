package com.example.architecturestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturestudy.R
import com.example.architecturestudy.model.CoinInfo
import com.example.architecturestudy.model.TickerResponse
import com.example.architecturestudy.ui.adapter.CoinAdapter
import com.example.architecturestudy.network.UpbitListener
import com.example.architecturestudy.network.UpbitRequest
import kotlinx.android.synthetic.main.fragment_list_coin.*

class CoinFragment : Fragment() {

    private var currencyList: List<String>? = null
    private val coinAdapter by lazy { CoinAdapter() }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return return inflater.inflate(R.layout.fragment_list_coin, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currencyList = arguments?.getStringArrayList(CURRENCY_LIST)
        currencyList?.let {
            getTickerInfo(it.joinToString(","))
        }

        setCoinAdapter()

    }

    private fun setCoinAdapter() {

        rv_coin_list.run {
            addItemDecoration(DividerItemDecoration(context, 1))
            layoutManager = LinearLayoutManager(requireContext())
            adapter = coinAdapter
        }

    }

    private fun getTickerInfo(name: String) {

        UpbitRequest().apply {
            getTickerInfo(name, object : UpbitListener<TickerResponse> {

                override fun onResponse(dataList: List<TickerResponse>) {

                    var list = mutableListOf<CoinInfo>()
                    dataList.forEach {
                        val market = it.market.split("-")
                        val currencyType = market[0]
                        val coinName = market[1]
                        val presentPrice = it.tradePrice
                        val signedChangeRate: Double = it.signedChangeRate
                        val transactionAmount: Double = it.accTradePrice24h

                        list.add(CoinInfo(currencyType, coinName, presentPrice, signedChangeRate, transactionAmount))
                    }

                    coinAdapter.setData(list)
                }

                override fun onFailure(str: String) {

                }

            })
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