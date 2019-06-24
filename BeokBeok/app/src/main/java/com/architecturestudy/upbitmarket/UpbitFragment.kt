package com.architecturestudy.upbitmarket

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.architecturestudy.R
import com.architecturestudy.base.BaseFragment
import com.architecturestudy.data.upbit.UpbitRepository
import com.architecturestudy.data.upbit.service.UpbitRetrofit
import com.architecturestudy.data.upbit.source.UpbitRetrofitDataSource
import com.architecturestudy.upbitmarket.recyclerview.DividerItemDecoration
import com.architecturestudy.upbitmarket.recyclerview.UpbitAdapter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_upbit.*

class UpbitFragment : BaseFragment(
    R.layout.fragment_upbit
), UpbitContract.View {

    override val presenter: UpbitContract.Presenter by lazy {
        UpbitPresenter(
            this,
            UpbitRepository(UpbitRetrofitDataSource(UpbitRetrofit.retrofit))
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.start()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
        initRecyclerView()
    }

    override fun onDestroy() {
        CompositeDisposable().dispose()
        super.onDestroy()
    }

    override fun updateMarketPrice(marketPrice: List<Map<String, String>>) {
        setMainAdapter(marketPrice)
    }

    override fun showErrMsg(t: Throwable) {
        showToast(t.message)
    }

    private fun initRecyclerView() {
        rv_coin_price.addItemDecoration(
            DividerItemDecoration(
                resources.getDrawable(R.drawable.line_divider, null)
            )
        )
        rv_coin_price.setHasFixedSize(true)
    }

    private fun setMainAdapter(marketPrice: List<Map<String, String>>) {
        val adapter = UpbitAdapter(R.layout.rv_upbit_item)
        adapter.setMarketPrices(marketPrice.sortedBy { it["coinName"] })
        rv_coin_price.adapter = adapter
    }

    private fun initClickListener() {
        tv_market_krw.setOnClickListener { setViewColor(tv_market_krw) }
        tv_market_btc.setOnClickListener { setViewColor(tv_market_btc) }
        tv_market_eth.setOnClickListener { setViewColor(tv_market_eth) }
        tv_market_usdt.setOnClickListener { setViewColor(tv_market_usdt) }
    }

    private fun setViewColor(view: View) {
        listOf<View>(
            tv_market_krw,
            tv_market_btc,
            tv_market_eth,
            tv_market_usdt
        ).filter { view != it }
            .map { (it as TextView).setTextColor(Color.GRAY) }

        if (view is TextView) {
            view.setTextColor(Color.BLUE)
            presenter.showMarketPrice(view.text.toString())
        }
    }
}
