package com.jskim5923.architecturestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jskim5923.architecturestudy.R
import com.jskim5923.architecturestudy.adapter.CoinListAdapter
import com.jskim5923.architecturestudy.coin.CoinContract
import com.jskim5923.architecturestudy.coin.CoinPresenter
import com.jskim5923.architecturestudy.model.Ticker
import kotlinx.android.synthetic.main.layout_coin_fragment.*

class CoinFragment : Fragment(), CoinContract.View {
    private var coinListAdapter = CoinListAdapter()

    override val presenter = CoinPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_coin_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView.adapter = coinListAdapter

        presenter.getTickerList(arguments?.getString(KEY_MARKET))
    }

    override fun updateRecyclerView(tickerList: List<Ticker>) {
        coinListAdapter.updateItem(tickerList)
    }

    override fun onDestroyView() {
        presenter.clearCompositeDisposable()
        super.onDestroyView()
    }

    companion object {
        const val KEY_MARKET = "KEY_MARKET"

        fun newInstance(market: String) = CoinFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_MARKET, market)
            }
        }
    }

}