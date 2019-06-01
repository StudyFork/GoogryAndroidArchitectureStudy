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
import kotlin.properties.Delegates


class CoinListFragment
    : BaseFragment<FragmentCoinListBinding>(R.layout.fragment_coin_list) {

    private lateinit var adapter: CoinListAdapter
    private var timer: Timer = Timer()

    private val viewModel by lazy {
        CoinListViewModel(
            TickerRepository.getInstance(
                TickerLocalDataSourceImpl.getInstance(),
                TickerRemoteDataSourceImpl.getInstance(UpbitService.upbitApi)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
        initSpinner()
    }


    fun initLayout() {
        adapter = CoinListAdapter()
        binding.recyclerView.adapter = this.adapter

        binding.search.addTextChangedListener(object : BaseTextWatcher {
            override fun afterTextChanged(s: Editable) {
                viewModel.search(s.toString())
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
                viewModel.changeBaseCurrency(parent.getItemAtPosition(position).toString())
            }
        }
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

    fun showCoinList(ticker: List<Ticker>) {
        adapter.setList(ticker)
    }

    fun showLoading() {
        setProgress(true)
        setRecyclerView(false)
    }

    fun hideLoading() {
        setProgress(false)
        setRecyclerView(true)
    }

    fun setProgress(boolean: Boolean) {
        binding.progress.visibility = if (boolean) View.VISIBLE else View.GONE
    }

    fun setRecyclerView(boolean: Boolean) {
        binding.recyclerView.visibility = if (boolean) View.VISIBLE else View.GONE
    }


    companion object {
        private const val SECOND = 1000L
        fun newInstance() = CoinListFragment()
    }


    /*
    * ViewModel
    * */
    inner class CoinListViewModel(
        val tickerRepository: TickerRepository
    ) {
        private var coinList by Delegates.observable<List<Ticker>>(arrayListOf()) { _, _, newValue ->
            showFilterCoinList()
        }
        private var searchStr by Delegates.observable("") { _, _, newValue ->
            showFilterCoinList()
        }
        private var baseCurrency by Delegates.observable("") { _, _, newValue ->
            initMarket()
        }
        private var isLoading by Delegates.observable(false) { _, _, newValue ->
            if(newValue) showLoading() else hideLoading()
        }
        private var errorMsg by Delegates.observable(""){_,_,newValue ->
            showError(newValue)
        }
        private lateinit var markets: String


        fun loadCoinList() {
            if (!this::markets.isInitialized) {
                initMarket()
            } else {
                getTickers(markets)
            }
        }

        fun changeBaseCurrency(baseCurrency: String) {
            this.baseCurrency = baseCurrency
        }

        fun search(searchStr: String) {
            this.searchStr = searchStr
        }

        fun initMarket() {
            isLoading=true
            tickerRepository.getMarketAll(
                baseCurrency,
                success = {
                    markets = it
                    getTickers(markets)
                },
                fail = {
                    errorMsg=it
                    isLoading =false
                }
            )
        }

        fun getTickers(markets: String) {
            tickerRepository.getTickers(
                markets,
                success = {
                    coinList = it
                    isLoading =false
                },
                fail = {
                    errorMsg=it
                    isLoading =false
                }
            )
        }

        private fun showFilterCoinList() {
            showCoinList(coinList.filter { it.name.contains(searchStr.toUpperCase()) })
        }
    }
}
