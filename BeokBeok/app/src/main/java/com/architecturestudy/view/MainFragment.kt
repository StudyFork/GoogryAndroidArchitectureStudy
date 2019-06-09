package com.architecturestudy.view


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.architecturestudy.R
import com.architecturestudy.common.UpBitCommunicable
import com.architecturestudy.common.UpBitConnector
import com.architecturestudy.model.UpBitTickerResponse
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), UpBitCommunicable {
    init {
        UpBitConnector(this).getMarketPrice("KRW")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onSuccessMarketPrice(marketPrice: List<UpBitTickerResponse>) {
        val context = activity as MainActivity
        rv_coin_price.addItemDecoration(DividerItemDecoration(context))
        val adapter = MainAdapter(context, marketPrice.sortedBy { it.market })
        rv_coin_price.adapter = adapter

        val lm = LinearLayoutManager(context)
        rv_coin_price.layoutManager = lm
        rv_coin_price.setHasFixedSize(true)
    }
}
