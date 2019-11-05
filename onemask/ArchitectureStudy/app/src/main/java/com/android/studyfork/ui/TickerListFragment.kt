package com.android.studyfork.ui


import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.android.studyfork.R
import com.android.studyfork.base.BaseFragment
import com.android.studyfork.databinding.FragmentTickerListBinding
import com.android.studyfork.ui.adapter.CoinItemAdapter
import com.android.studyfork.ui.tickerlist.viewmodel.TickerViewModel

class TickerListFragment :
    BaseFragment<FragmentTickerListBinding, TickerViewModel>(R.layout.fragment_ticker_list) {

    private val coinItemAdapter = CoinItemAdapter()

    override val viewModel by lazy { ViewModelProviders.of(this)[TickerViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        getTicker()
    }

    private fun setRecyclerView() {
        binding.run {
            lifecycleOwner = this@TickerListFragment
            recyclerview.apply {
                adapter = coinItemAdapter
                setHasFixedSize(false)
            }
            tickerViewModel = viewModel
        }
    }

    private fun getTicker() {
        val market = arguments?.getString(KEY_MARKETS) ?: ""
        viewModel.getTicker(market)
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


