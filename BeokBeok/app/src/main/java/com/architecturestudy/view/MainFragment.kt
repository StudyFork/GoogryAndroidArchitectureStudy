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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UpBitConnector(this).getMarketPrice("KRW")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
    }

    override fun onSuccessMarketPrice(marketPrice: List<UpBitTickerResponse>) = setMainAdapter(marketPrice)

    private fun initRecyclerView() {
        val context = activity as MainActivity
        rv_coin_price.addItemDecoration(DividerItemDecoration(context))
        val lm = LinearLayoutManager(context)
        rv_coin_price.layoutManager = lm
        rv_coin_price.setHasFixedSize(true)
    }

    private fun setMainAdapter(marketPrice: List<UpBitTickerResponse>) {
        val adapter = MainAdapter()
        adapter.setMarketPrices(marketPrice.sortedBy { it.market })
        rv_coin_price.adapter = adapter
    }
}
