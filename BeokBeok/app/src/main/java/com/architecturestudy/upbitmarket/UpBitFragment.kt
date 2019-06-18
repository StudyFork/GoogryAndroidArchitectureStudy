package com.architecturestudy.upbitmarket


import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.architecturestudy.R
import com.architecturestudy.common.BaseFragment
import com.architecturestudy.data.UpBitRepository
import com.architecturestudy.data.UpBitTicker
import com.architecturestudy.data.source.UpBitRetrofitDataSource
import com.architecturestudy.upbitmarket.recyclerview.DividerItemDecoration
import com.architecturestudy.upbitmarket.recyclerview.UpBitAdapter
import kotlinx.android.synthetic.main.fragment_upbit.*

class UpBitFragment : BaseFragment(), UpBitContract.View {

    private lateinit var upBitPresenter: UpBitPresenter
    override lateinit var presenter: UpBitContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        upBitPresenter = UpBitPresenter(UpBitRepository.getInstance(UpBitRetrofitDataSource.getInstance()), this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_upbit, container, false)
        presenter.start()
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initClickListener()
        initRecyclerView()
    }

    override fun updateMarketPrice(marketPrice: List<UpBitTicker>) {
        setMainAdapter(marketPrice)
    }

    override fun showErrMsg(err: String) {
        showToast(err)
    }

    private fun initRecyclerView() {
        val context = activity as UpBitActivity
        rv_coin_price.addItemDecoration(DividerItemDecoration(context))
        val lm = LinearLayoutManager(context)
        rv_coin_price.layoutManager = lm
        rv_coin_price.setHasFixedSize(true)
    }

    private fun setMainAdapter(marketPrice: List<UpBitTicker>) {
        val adapter = UpBitAdapter()
        adapter.setMarketPrices(marketPrice.sortedBy { it.market })
        rv_coin_price.adapter = adapter
    }

    private fun initClickListener() {
        tv_market_krw.setOnClickListener {
            tv_market_krw.setTextColor(Color.BLUE)
            tv_market_btc.setTextColor(Color.GRAY)
            tv_market_eth.setTextColor(Color.GRAY)
            tv_market_usdt.setTextColor(Color.GRAY)
            presenter.showMarketPrice(tv_market_krw.text.toString())
        }
        tv_market_btc.setOnClickListener {
            tv_market_krw.setTextColor(Color.GRAY)
            tv_market_btc.setTextColor(Color.BLUE)
            tv_market_eth.setTextColor(Color.GRAY)
            tv_market_usdt.setTextColor(Color.GRAY)
            presenter.showMarketPrice(tv_market_btc.text.toString())
        }
        tv_market_eth.setOnClickListener {
            tv_market_krw.setTextColor(Color.GRAY)
            tv_market_btc.setTextColor(Color.GRAY)
            tv_market_eth.setTextColor(Color.BLUE)
            tv_market_usdt.setTextColor(Color.GRAY)
            presenter.showMarketPrice(tv_market_eth.text.toString())
        }
        tv_market_usdt.setOnClickListener {
            tv_market_krw.setTextColor(Color.GRAY)
            tv_market_btc.setTextColor(Color.GRAY)
            tv_market_eth.setTextColor(Color.GRAY)
            tv_market_usdt.setTextColor(Color.BLUE)
            presenter.showMarketPrice(tv_market_usdt.text.toString())
        }
    }
}
