package com.namjackson.archstudy.view.coinlist

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.namjackson.archstudy.R
import com.namjackson.archstudy.base.BaseFragment
import com.namjackson.archstudy.data.source.TickerRepository
import com.namjackson.archstudy.data.source.local.TickerLocalDataSourceImpl
import com.namjackson.archstudy.data.source.remote.TickerRemoteDataSourceImpl
import com.namjackson.archstudy.databinding.FragmentCoinListBinding
import com.namjackson.archstudy.network.UpbitService
import com.namjackson.archstudy.view.coinlist.adapter.CoinListAdapter
import java.util.*


class CoinListFragment
    : BaseFragment<FragmentCoinListBinding>(R.layout.fragment_coin_list) {

    private var timer: Timer = Timer()

    val viewModel by lazy {
        CoinListViewModel(
            TickerRepository.getInstance(
                TickerLocalDataSourceImpl.getInstance(),
                TickerRemoteDataSourceImpl.getInstance(UpbitService.upbitApi)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind {

            spinner.adapter = ArrayAdapter(
                context,
                android.R.layout.simple_spinner_item,
                arrayOf("KRW", "BTC", "ETH", "USDT")
            )

            viewmodel = viewModel
            lifecycleOwner = this@CoinListFragment.viewLifecycleOwner
            recyclerView.adapter = CoinListAdapter()
        }

        subscribeToShowToast()

        viewModel.baseCurrency.observe(this, androidx.lifecycle.Observer { viewModel.initMarket() })
    }


    override fun onResume() {
        super.onResume()
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                viewModel.loadCoinList()
            }
        }, 0, (10 * SECOND))
    }

    override fun onPause() {
        timer.cancel()
        super.onPause()
    }

    private fun subscribeToShowToast() {
        viewModel.showToastEvent.observe(this, androidx.lifecycle.Observer { event ->
            event?.getContentIfNotHandled()?.let {
                showToast(it)
            }
        })
    }

    companion object {
        private const val SECOND = 1000L
        fun newInstance() = CoinListFragment()
    }

}
