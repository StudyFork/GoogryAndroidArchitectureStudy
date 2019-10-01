package com.example.architecturestudy.ui.market

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import com.example.architecturestudy.R
import com.example.architecturestudy.base.BaseFragment
import com.example.architecturestudy.data.source.CoinsRepositoryImpl
import com.example.architecturestudy.data.source.local.CoinsLocalDataSource
import com.example.architecturestudy.data.source.remote.CoinsRemoteDataSource
import com.example.architecturestudy.databinding.FragmentMarketBinding
import com.example.architecturestudy.network.RetrofitHelper
import com.example.architecturestudy.viewmodel.MarketViewModel

class MarketFragment : BaseFragment<FragmentMarketBinding>(R.layout.fragment_market) {

    private val marketViewModel by lazy {
        MarketViewModel(
            CoinsRepositoryImpl.getInstance(
                CoinsRemoteDataSource.getInstance(RetrofitHelper.getInstance().coinApiService),
                CoinsLocalDataSource.getInstance()
            )
        )
    }

    private val rvAdapter = RecyclerViewAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViewModel()
        initRecyclerView()
        initCallback()

        marketViewModel.loadData(arguments?.getString(KEY_MARKET))
    }

    private fun initViewModel() {
        binding.viewModel = marketViewModel // 뷰모델을 레이아웃과 바인딩
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = rvAdapter
    }

    private fun initCallback() {
        marketViewModel.notificationMsg.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                (sender as ObservableField<String>).get()?.let { msg ->
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    companion object {
        private const val KEY_MARKET = "KEY_MARKET"

        fun newInstance(market: String) = MarketFragment().apply {
            arguments = Bundle().apply { putString(KEY_MARKET, market) }
        }
    }

}