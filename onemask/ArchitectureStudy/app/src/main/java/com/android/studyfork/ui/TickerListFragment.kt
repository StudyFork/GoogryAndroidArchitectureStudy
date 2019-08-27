package com.android.studyfork.ui


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.studyfork.R
import com.android.studyfork.network.model.Ticker
import com.android.studyfork.repository.UpbitRepositoryImpl
import com.android.studyfork.ui.adapter.CoinItemAdapter
import com.android.studyfork.util.inflate
import kotlinx.android.synthetic.main.fragment_ticker_list.*
import timber.log.Timber


class TickerListFragment : Fragment() {

    private val upbitRepository = UpbitRepositoryImpl
    private lateinit var coinItemAdapter: CoinItemAdapter

    @SuppressLint("CheckResult")
    private fun getTicker(){
        val markets = arguments?.getString(KEY_MARKETS) ?:""
        val tickerList:ArrayList<Ticker> = arrayListOf()

        upbitRepository.getTickers(markets)
            .subscribe({
                Timber.d("getTicker success")
                it.forEachIndexed { index, tickerResponse ->
                    tickerList.add(index,
                        Ticker(tickerResponse.market,
                               tickerResponse.tradePrice,
                               tickerResponse.signedChangeRate,
                               tickerResponse.accTradePrice24h))
                }
                coinItemAdapter.setData(tickerList)
            },{
                Timber.e(it)
            })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = container?.inflate(R.layout.fragment_ticker_list)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        getTicker()

    }

    private fun setRecyclerView() {
        coinItemAdapter = CoinItemAdapter()
        recyclerview.apply {
            adapter = coinItemAdapter
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


