package com.architecturestudy.upbitmarket


import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.architecturestudy.R
import com.architecturestudy.base.BaseFragment
import com.architecturestudy.data.UpbitRepository
import com.architecturestudy.data.UpbitTicker
import com.architecturestudy.data.source.UpbitRetrofitDataSource
import com.architecturestudy.upbitmarket.recyclerview.DividerItemDecoration
import com.architecturestudy.upbitmarket.recyclerview.UpbitAdapter
import kotlinx.android.synthetic.main.fragment_upbit.*

class UpbitFragment : BaseFragment(), UpbitContract.View {

    override val presenter: UpbitContract.Presenter by lazy {
        UpbitPresenter(this, UpbitRepository(UpbitRetrofitDataSource()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_upbit, container, false)
        presenter.start()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
        initRecyclerView()
    }

    override fun updateMarketPrice(marketPrice: List<UpbitTicker>) {
        setMainAdapter(marketPrice)
    }

    override fun showErrMsg(t: Throwable) {
        showToast(t.message)
    }

    private fun initRecyclerView() {
        val context = activity as UpbitActivity
        rv_coin_price.addItemDecoration(DividerItemDecoration(context))
        val lm = LinearLayoutManager(context)
        rv_coin_price.layoutManager = lm
        rv_coin_price.setHasFixedSize(true)
    }

    private fun setMainAdapter(marketPrice: List<UpbitTicker>) {
        val adapter = UpbitAdapter()
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
