package com.android.studyfork.ui


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.studyfork.R
import com.android.studyfork.network.model.Ticker
import com.android.studyfork.repository.UpbitRepositoryImpl
import com.android.studyfork.ui.adapter.CoinItemAdapter
import com.android.studyfork.ui.tickerlist.presenter.TickerContract
import com.android.studyfork.ui.tickerlist.presenter.TickerPresenter
import com.android.studyfork.util.inflate
import kotlinx.android.synthetic.main.fragment_ticker_list.*


class TickerListFragment : Fragment(), TickerContract.View {

    private lateinit var coinItemAdapter: CoinItemAdapter
    private val tickerPresenter by lazy { TickerPresenter(UpbitRepositoryImpl, this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = container?.inflate(R.layout.fragment_ticker_list)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        getTicker()
    }

    @SuppressLint("CheckResult")
    private fun getTicker() {
        val market = arguments?.getString(KEY_MARKETS) ?: ""
        tickerPresenter.getTicker(market)
    }

    override fun setRecyclerView() {
        coinItemAdapter = CoinItemAdapter()
        recyclerview.apply {
            adapter = coinItemAdapter
            setHasFixedSize(false)
        }
    }

    override fun setData(ticker: List<Ticker>) {
        coinItemAdapter.setData(ticker)
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


