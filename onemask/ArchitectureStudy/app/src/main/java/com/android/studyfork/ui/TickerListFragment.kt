package com.android.studyfork.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.studyfork.R
import com.android.studyfork.data.repository.UpbitRepository
import com.android.studyfork.network.api.UpbitService
import com.android.studyfork.network.remote.model.Coin
import com.android.studyfork.ui.adpater.CoinItemAdapter
import com.android.studyfork.utill.filterTrade
import com.android.studyfork.utill.inflate
import com.android.studyfork.utill.setBeforeRate
import com.android.studyfork.utill.setTradeAmount
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_krw.*
import timber.log.Timber

class TickerListFragment : Fragment() {

    private val upbitRepository: UpbitRepository by lazy { UpbitRepository(UpbitService.getInstance().upbitApi) }

    private lateinit var coinItemAdapter: CoinItemAdapter


    @SuppressLint("CheckResult")
    private fun getTicker(){
        val markets = arguments?.getString(KEY_MARKETS) ?:""
        upbitRepository.getTickers(markets)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                coinItemAdapter.setData(
                    it.map {
                        Coin(
                            title = it.market.split("-")[1],
                            textCurrentPrice = it.tradePrice.filterTrade(it.tradePrice),
                            textBeforeDay = it.signedChangeRate.setBeforeRate(it.signedChangeRate),
                            textTotalTrade = it.accTradePrice24h.setTradeAmount(it.accTradePrice24h),
                            coinColor = if (it.signedChangePrice >= 0) Color.RED else Color.BLUE
                        )
                    }
                )
                Timber.d("getTicker success")
            },{
                Timber.e("${it.printStackTrace()}")
            })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = container?.inflate(R.layout.fragment_krw)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        getTicker()

    }

    private fun setRecyclerView() {
        coinItemAdapter = CoinItemAdapter()
        recyclerview.apply {
            adapter = coinItemAdapter
            addItemDecoration(DividerItemDecoration(context,1))
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(false)
        }
    }
    companion object {
        const val KEY_MARKETS = "markets"
        fun newInstance(tickers: String) = TickerListFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_MARKETS, tickers)
            }
        }
    }


}


