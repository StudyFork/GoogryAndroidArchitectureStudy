package com.architecture.study.view.coin

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.architecture.study.BR
import com.architecture.study.R
import com.architecture.study.base.BaseAdapter
import com.architecture.study.base.BaseFragment
import com.architecture.study.data.model.Ticker
import com.architecture.study.databinding.FragmentCoinlistBinding
import com.architecture.study.databinding.ItemTickerBinding
import com.architecture.study.viewmodel.TickerViewModel
import com.architecture.study.viewmodel.factory.TickerViewModelFactory

class CoinListFragment : BaseFragment<FragmentCoinlistBinding>(R.layout.fragment_coinlist) {

    private val tickerViewModel by lazy {
        ViewModelProviders
            .of(this, TickerViewModelFactory(tabList.map { getString(it) }))
            .get(TickerViewModel::class.java)
    }

    private val tabList = listOf(
        R.string.monetary_unit_1,
        R.string.monetary_unit_2,
        R.string.monetary_unit_3,
        R.string.monetary_unit_4
    )

    @Suppress("UNCHECKED_CAST")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.run {
            tickerVM = tickerViewModel
            recyclerViewCoinList.adapter =
                object : BaseAdapter<Ticker, ItemTickerBinding>(R.layout.item_ticker, BR.ticker) {}
        }

        tickerViewModel
            .exceptionMessage
            .observe(this@CoinListFragment, Observer {
                showMessage(it)
            })

    }

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun setMonetaryUnitList(monetaryUnitList: List<String>) {
        tickerViewModel.getTickerList(monetaryUnitList)
    }

    companion object {
        fun newInstance() = CoinListFragment()
    }
}