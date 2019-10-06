package com.test.androidarchitecture.ui.ticker


import android.os.Bundle
import com.test.androidarchitecture.R
import com.test.androidarchitecture.adpter.TickerAdapter
import com.test.androidarchitecture.base.BaseFragment
import com.test.androidarchitecture.data.TickerFormat
import kotlinx.android.synthetic.main.fragment_coin.*


class TickerFragment :
    BaseFragment<TickerContract.Presenter>(R.layout.fragment_coin),
    TickerContract.View {

    private val adapter by lazy { TickerAdapter() }
    override val presenter by lazy { TickerPresenter(this, arguments?.getString(MARKET_SEARCH) ?: "") }

    override fun start() {
        coin_recyclerView.adapter = this.adapter
        presenter.getTicker()
    }

    override fun setTickerData(list: List<TickerFormat>) {
        adapter.setItem(list)
    }

    companion object {

        private const val MARKET_SEARCH: String = "marketSearch"

        fun getInstance(marketSearch: String): TickerFragment {
            val args = Bundle()
            args.putString(MARKET_SEARCH, marketSearch)
            val fragment = TickerFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
