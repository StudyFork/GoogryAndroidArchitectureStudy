package com.example.architecturestudy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.architecturestudy.data.Coin
import com.example.architecturestudy.data.source.CoinsRepository
import com.example.architecturestudy.data.source.local.CoinsLocalDataSource
import com.example.architecturestudy.data.source.remote.CoinsRemoteDataSource
import com.example.architecturestudy.network.RetrofitHelper
import com.example.architecturestudy.ui.MarketContract
import com.example.architecturestudy.ui.MarketPresenter
import com.example.architecturestudy.ui.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_market.*

class MarketFragment : Fragment(), MarketContract.View {

    private lateinit var presenter: MarketContract.Presenter // Presenter 프로퍼티 선언

    private var key = "KEY_MARKET"

    private val repository = CoinsRepository.getInstance(
        CoinsRemoteDataSource.getInstance(RetrofitHelper.getInstance().coinApiService),
        CoinsLocalDataSource.getInstance()
    )

    private val rvAdapter = RecyclerViewAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_market, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //리사이클러뷰 어댑터 설정
        recyclerView.apply {
            adapter = rvAdapter
        }

        val keyMarket = arguments?.getString(key)

        presenter = MarketPresenter(this, repository) // Presenter 객체 생성해서 할당
        presenter.loadData(keyMarket) // Presenter 를 통해 해당 마켓의 데이터 불러오기
    }

    override fun showTickerData(data: List<Coin>) {
        rvAdapter.setData(data)
    }

    override fun clearTickerData() {
        rvAdapter.clearData()
    }

    override fun showProgressBar() {
        pb_market.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        pb_market.visibility = View.GONE
    }


    companion object {
        fun newInstance(market: String) = MarketFragment().apply {
            arguments = Bundle().apply { putString("KEY_MARKET", market) }
        }
    }

}