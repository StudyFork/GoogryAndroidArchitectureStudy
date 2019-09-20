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

    private lateinit var marketViewModel: MarketViewModel
    private var key = "KEY_MARKET"
    private val rvAdapter = RecyclerViewAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRecyclerView()
        initViewModel()

        marketViewModel.loadData(arguments?.getString(key))
    }

    private fun initRecyclerView() {
        //리사이클러뷰 어댑터 설정
        binding.recyclerView.apply {
            adapter = rvAdapter
        }
    }

    private fun initViewModel() {
        marketViewModel = MarketViewModel(
            CoinsRepositoryImpl.getInstance(
                CoinsRemoteDataSource.getInstance(RetrofitHelper.getInstance().coinApiService),
                CoinsLocalDataSource.getInstance()
            )
        ) // MarketViewModel 객체 생성

        binding.viewModel = marketViewModel // 뷰모델을 레이아웃과 바인딩
    }

    companion object {
        fun newInstance(market: String) = MarketFragment().apply {
            arguments = Bundle().apply { putString(key, market) }
        }
    }

}