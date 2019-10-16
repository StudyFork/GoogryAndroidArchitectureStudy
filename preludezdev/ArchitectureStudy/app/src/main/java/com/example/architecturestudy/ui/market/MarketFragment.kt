package com.example.architecturestudy.ui.market

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.architecturestudy.R
import com.example.architecturestudy.base.BaseFragment
import com.example.architecturestudy.databinding.FragmentMarketBinding
import com.example.architecturestudy.util.Filter
import com.example.architecturestudy.viewmodel.MarketViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class MarketFragment : BaseFragment<FragmentMarketBinding>(R.layout.fragment_market) {

    private lateinit var marketViewModel: MarketViewModel
    private val rvAdapter = RecyclerViewAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViewModel()
        initRecyclerView()
        initCallback()
    }

    fun sortData(type: Filter.Type, order: Filter.Order) {
        marketViewModel.sortData(type, order)
    }

    fun refresh(type: Filter.Type, order: Filter.Order) {
        marketViewModel.loadData(type, order)
    }

    private fun initViewModel() {
        marketViewModel = getViewModel { parametersOf(arguments?.getString(KEY_MARKET)) }
        binding.viewModel = marketViewModel
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = rvAdapter
    }

    private fun initCallback() {
        marketViewModel.notificationMsg.observe(this, Observer<String> { errorMsg ->
            showToastMessage(errorMsg)
        })
    }

    companion object {
        private const val KEY_MARKET = "KEY_MARKET"

        fun newInstance(market: String) = MarketFragment().apply {
            arguments = Bundle().apply { putString(KEY_MARKET, market) }
        }
    }
}
