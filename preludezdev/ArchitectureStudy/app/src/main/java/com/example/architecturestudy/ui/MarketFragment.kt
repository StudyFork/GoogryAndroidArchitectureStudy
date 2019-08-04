package com.example.architecturestudy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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

    private val repository = CoinsRepository.getInstance(
        CoinsRemoteDataSource.getInstance(RetrofitHelper.getInstance().coinApiService),
        CoinsLocalDataSource.getInstance()
    )

    private val rvAdapter = RecyclerViewAdapter()

    companion object {
        fun newInstance(market: String): MarketFragment {
            val fragment = MarketFragment()
            val args = Bundle()

            when (market) {
                "KRW" -> args.putString("KEY_MARKET", "KRW")
                "BTC" -> args.putString("KEY_MARKET", "BTC")
                "ETH" -> args.putString("KEY_MARKET", "ETH")
                "USDT" -> args.putString("KEY_MARKET", "USDT")
            }

            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_market, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //리사이클러뷰 어댑터와 레이아웃매니저 설정
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = rvAdapter
        }

        val keyMarket = arguments?.getString("KEY_MARKET")

        presenter = MarketPresenter(this, repository) // Presenter 객체 생성해서 할당
        presenter.loadData(keyMarket) // Presenter 를 통해 해당 마켓의 데이터 불러오기
    }

    override fun setTickerData(data: List<Coin>) {
        rvAdapter.setData(data)
    }

    override fun clearTickerData() {
        rvAdapter.clearData()
    }

}