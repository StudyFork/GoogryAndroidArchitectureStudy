package com.android.studyfork.ui


import android.os.Bundle
import android.view.View
import com.android.studyfork.R
import com.android.studyfork.base.BaseFragment
import com.android.studyfork.network.model.Ticker
import com.android.studyfork.ui.adapter.CoinItemAdapter
import com.android.studyfork.ui.tickerlist.presenter.TickerContract
import com.android.studyfork.ui.tickerlist.presenter.TickerPresenter
import kotlinx.android.synthetic.main.fragment_ticker_list.*

class TickerListFragment : BaseFragment(R.layout.fragment_ticker_list), TickerContract.View {

    private lateinit var coinItemAdapter: CoinItemAdapter
    override val presenter by lazy { TickerPresenter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        getTicker()
    }

    override fun setData(ticker: List<Ticker>) {
        coinItemAdapter.setData(ticker)
    }

    override fun onDestroyView() {
        presenter.clearDisposable()
        super.onDestroyView()
    }

    private fun setRecyclerView() {
        coinItemAdapter = CoinItemAdapter()
        recyclerview.apply {
            adapter = coinItemAdapter
            setHasFixedSize(false)
        }
    }

    private fun getTicker() {
        val market = arguments?.getString(KEY_MARKETS) ?: ""
        presenter.getTicker(market)
    }

    companion object {
        const val KEY_MARKETS = "markets"
        fun newInstance(tickers: String) = TickerListFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_MARKETS, tickers)
            }
        }
    }


}


