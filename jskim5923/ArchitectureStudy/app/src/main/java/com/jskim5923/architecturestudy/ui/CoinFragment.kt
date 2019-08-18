package com.jskim5923.architecturestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jskim5923.architecturestudy.R
import com.jskim5923.architecturestudy.adapter.CoinListAdapter
import com.jskim5923.architecturestudy.api.ApiManager
import com.jskim5923.architecturestudy.extension.getCoinCurrency
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.layout_coin_fragment.*

class CoinFragment : Fragment() {
    private val compositeDisposable = CompositeDisposable()

    private var coinListAdapter = CoinListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_coin_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val market = arguments?.getString(KEY_MARKET)

        recyclerView.adapter = coinListAdapter

        recyclerView.layoutManager = LinearLayoutManager(requireActivity())


        compositeDisposable += ApiManager.coinApi.getMarketList()
            .subscribeOn(Schedulers.io())
            .map { marketList ->
                marketList.filter {
                    it.market.getCoinCurrency() == market
                }.joinToString(",") {
                    it.market
                }
            }
            .subscribe({ market ->
                ApiManager.coinApi.getTicker(market)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ tickerList ->
                        coinListAdapter.updateItem(tickerList)
                    }, { e ->
                        e.printStackTrace()
                    })
            }, { e ->
                e.printStackTrace()
            })
    }

    override fun onDestroyView() {
        compositeDisposable.clear()
        super.onDestroyView()
    }

    companion object {
        const val KEY_MARKET = "KEY_MARKET"

        fun newInstance(market: String) = CoinFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_MARKET, market)
            }
        }
    }

}