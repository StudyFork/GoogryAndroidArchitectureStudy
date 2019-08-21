package com.android.studyfork.ui


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.studyfork.R
import com.android.studyfork.network.api.UpbitService
import com.android.studyfork.repository.UpbitRepository
import com.android.studyfork.repository.UpbitRepositoryImpl
import com.android.studyfork.ui.adapter.CoinItemAdapter
import com.android.studyfork.util.inflate
import kotlinx.android.synthetic.main.fragment_ticker_list.*
import timber.log.Timber


class TickerListFragment : Fragment() {

    private val upbitService by lazy{ UpbitService.getInstance().upbitDataSource }

    private lateinit var upbitRepository: UpbitRepository
    private lateinit var coinItemAdapter: CoinItemAdapter

    @SuppressLint("CheckResult")
    private fun getTicker(){
        val markets = arguments?.getString(KEY_MARKETS) ?:""
        upbitRepository.getTikcers(markets)
            .subscribe({
                Timber.d("getTicker success")
                coinItemAdapter.setData(it)
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
        upbitRepository = UpbitRepositoryImpl(upbitService)
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


