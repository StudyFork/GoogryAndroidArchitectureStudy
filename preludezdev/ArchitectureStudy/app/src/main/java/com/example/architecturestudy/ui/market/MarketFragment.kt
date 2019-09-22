package com.example.architecturestudy.ui.market

import android.os.Bundle
import com.example.architecturestudy.R
import com.example.architecturestudy.base.BaseFragment
import com.example.architecturestudy.data.source.CoinsRepositoryImpl
import com.example.architecturestudy.data.source.local.CoinsLocalDataSource
import com.example.architecturestudy.data.source.remote.CoinsRemoteDataSource
import com.example.architecturestudy.databinding.FragmentMarketBinding
import com.example.architecturestudy.network.RetrofitHelper
import com.example.architecturestudy.viewmodel.MarketViewModel

class MarketFragment : BaseFragment<FragmentMarketBinding>(R.layout.fragment_market) {

    private val marketViewModel: MarketViewModel by lazy {
        MarketViewModel(
            CoinsRepositoryImpl.getInstance(
                CoinsRemoteDataSource.getInstance(RetrofitHelper.getInstance().coinApiService),
                CoinsLocalDataSource.getInstance()
            )
        )
    }

    private var keyMarket = "KEY_MARKET"
    private val rvAdapter = RecyclerViewAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViewModel()
        initRecyclerView()

        marketViewModel.loadData(arguments?.getString(keyMarket))
    }

    private fun initViewModel() {
        binding.viewModel = marketViewModel // 뷰모델을 레이아웃과 바인딩
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = rvAdapter
    }

    companion object {
        fun newInstance(market: String) = MarketFragment().apply {
            arguments = Bundle().apply { putString(keyMarket, market) }
        }
    }

}