package com.namjackson.archstudy.view.coinlist

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.namjackson.archstudy.R
import com.namjackson.archstudy.base.BaseFragment
import com.namjackson.archstudy.base.component.BaseOnItemSelectedListener
import com.namjackson.archstudy.base.component.BaseTextWatcher
import com.namjackson.archstudy.data.model.Ticker
import com.namjackson.archstudy.data.source.TickerRepository
import com.namjackson.archstudy.data.source.local.TickerLocalDataSourceImpl
import com.namjackson.archstudy.data.source.remote.TickerRemoteDataSourceImpl
import com.namjackson.archstudy.databinding.FragmentCoinListBinding
import com.namjackson.archstudy.network.UpbitService
import com.namjackson.archstudy.view.coinlist.adapter.CoinListAdapter
import java.util.*


class CoinListFragment
    : BaseFragment<FragmentCoinListBinding, CoinListContract.Presenter>(R.layout.fragment_coin_list),
    CoinListContract.View {


    private lateinit var adapter: CoinListAdapter
    private var timer: Timer = Timer()

    override fun showCoinList(ticker: List<Ticker>) {
        adapter.setList(ticker)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = CoinListPresenter(
            TickerRepository.getInstance(
                TickerLocalDataSourceImpl.getInstance(),
                TickerRemoteDataSourceImpl.getInstance(UpbitService.upbitApi)
            ),
            this
        )


        initLayout()
        initSpinner()
    }


    fun initLayout() {
        adapter = CoinListAdapter()
        binding.recyclerView.adapter = this.adapter

        binding.search.addTextChangedListener(object : BaseTextWatcher {
            override fun afterTextChanged(s: Editable) {
                presenter.search(s.toString())
            }
        })
    }

    fun initSpinner() {
        binding.spinner.adapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item,
            arrayOf("KRW", "BTC", "ETH", "USDT")
        )

        binding.spinner.onItemSelectedListener = object : BaseOnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, p1: View?, position: Int, p3: Long) {
                presenter.changeBaseCurrency(parent.getItemAtPosition(position).toString())
            }
        }
    }

    override fun onResume() {
        super.onResume()
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                presenter.loadCoinList()
            }
        }, 0, (10 * SECOND))
    }

    override fun onPause() {
        timer.cancel()
        super.onPause()
    }

    override fun showProgress() {
        binding.progress.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
    }

    override fun hideProgress() {
        binding.progress.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
    }


    companion object {

        private const val SECOND = 1000L
        fun newInstance() = CoinListFragment()
    }
}
