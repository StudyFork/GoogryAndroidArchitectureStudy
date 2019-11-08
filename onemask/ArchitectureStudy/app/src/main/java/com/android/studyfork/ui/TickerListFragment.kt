package com.android.studyfork.ui


import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.android.studyfork.R
import com.android.studyfork.base.BaseFragment
import com.android.studyfork.databinding.FragmentTickerListBinding
import com.android.studyfork.ui.adapter.CoinItemAdapter
import com.android.studyfork.ui.tickerlist.viewmodel.TickerViewModel
import com.android.studyfork.ui.tickerlist.viewmodel.TickerViewModelFactory

class TickerListFragment :
    BaseFragment<FragmentTickerListBinding, TickerViewModel>(R.layout.fragment_ticker_list) {

    private val coinItemAdapter = CoinItemAdapter()

    private val viewModelFactory by lazy {
        TickerViewModelFactory(
            arguments?.getString(KEY_MARKETS) ?: ""
        )
    }

    override val viewModel by lazy {
        ViewModelProviders.of(
            this,
            viewModelFactory
        )[TickerViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.run {
            recyclerview.apply {
                adapter = coinItemAdapter
                setHasFixedSize(false)
            }
            tickerViewModel = viewModel
        }
    }

    companion object {
        const val KEY_MARKETS = "KEY_MARKETS"
        fun newInstance(tickers: String) = TickerListFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_MARKETS, tickers)
            }
        }
    }


}


