package com.example.architecturestudy.ui.market

import android.os.Bundle
import android.view.View
import com.example.architecturestudy.R
import com.example.architecturestudy.base.BaseFragment
import com.example.architecturestudy.data.Coin
import com.example.architecturestudy.data.source.CoinsRepositoryImpl
import com.example.architecturestudy.data.source.local.CoinsLocalDataSource
import com.example.architecturestudy.data.source.remote.CoinsRemoteDataSource
import com.example.architecturestudy.databinding.FragmentMarketBinding
import com.example.architecturestudy.network.RetrofitHelper

class MarketFragment : BaseFragment<FragmentMarketBinding>(R.layout.fragment_market),
    MarketContract.View {

    private lateinit var presenter: MarketContract.Presenter // Presenter 프로퍼티 선언
    private var key = "KEY_MARKET"
    private val rvAdapter = RecyclerViewAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRecyclerView()
        initPresenter()

        presenter.loadData(arguments?.getString(key)) // Presenter 를 통해 해당 마켓의 데이터 불러오기
    }

    private fun initRecyclerView() {
        //리사이클러뷰 어댑터 설정
        binding.recyclerView.apply {
            adapter = rvAdapter
        }
    }

    private fun initPresenter() {
        presenter = MarketPresenter(
            this, CoinsRepositoryImpl.getInstance(
                CoinsRemoteDataSource.getInstance(RetrofitHelper.getInstance().coinApiService),
                CoinsLocalDataSource.getInstance()
            )
        ) // Presenter 객체 생성해서 할당
    }

    override fun showTickerData(data: List<Coin>) {
        rvAdapter.setData(data)
    }

    override fun clearTickerData() {
        rvAdapter.clearData()
    }

    override fun showProgressBar() {
        binding.pbMarket.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.pbMarket.visibility = View.GONE
    }

    companion object {
        fun newInstance(market: String) = MarketFragment().apply {
            arguments = Bundle().apply { putString(key, market) }
        }
    }

}