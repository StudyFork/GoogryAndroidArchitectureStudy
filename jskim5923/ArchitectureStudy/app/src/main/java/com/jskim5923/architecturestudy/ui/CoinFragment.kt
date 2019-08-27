package com.jskim5923.architecturestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jskim5923.architecturestudy.R
import com.jskim5923.architecturestudy.adapter.CoinListAdapter
import com.jskim5923.architecturestudy.api.ApiManager
import com.jskim5923.architecturestudy.extension.getCoinCurrency
import com.jskim5923.architecturestudy.model.data.source.Repository
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val market = arguments?.getString(KEY_MARKET)

        recyclerView.adapter = coinListAdapter

        compositeDisposable += Repository.getMarketList()
            .subscribeOn(Schedulers.io())
            .flatMap { marketList ->
                ApiManager.coinApi.getTicker(
                    marketList.filter {
                        it.market.getCoinCurrency() == market
                    }.joinToString(",") {
                        it.market
                    }
                )
            }
            .flattenAsObservable { tickerResponseList ->
                tickerResponseList.map { tickerResponse ->
                    tickerResponse.toTicker()
                }
            }
            .toList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ tickerList ->
                coinListAdapter.updateItem(tickerList)
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