package com.namjackson.archstudy.view.coinlist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.namjackson.archstudy.R
import com.namjackson.archstudy.base.BaseFragment
import com.namjackson.archstudy.data.Ticker
import com.namjackson.archstudy.data.repository.UpbitRepository
import com.namjackson.archstudy.databinding.FragmentCoinListBinding
import com.namjackson.archstudy.network.UpbitService
import com.namjackson.archstudy.view.coinlist.adapter.CoinListAdapter
import java.util.*


private const val BASE_CURRENCY = "BASE_CURRENCY"
private const val SECOND = 1000L

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
        var baseCurrency: String = ""
        arguments?.let {
            baseCurrency = it.getString(BASE_CURRENCY) ?: ""
        }
        presenter = CoinListPresenter(
            baseCurrency,
            UpbitRepository.getInstance(UpbitService.upbitApi),
            this as CoinListContract.View
        )

        initLayout()
        initSpinner()
    }


    fun initLayout() {
        adapter = CoinListAdapter()
        binding.recyclerView.adapter = this.adapter

        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

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
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                presenter.changeBaseCurrency(parent.getItemAtPosition(position).toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    override fun onResume() {
        super.onResume()
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
        fun newInstance(baseCurrency: String) =
            CoinListFragment().apply {
                arguments = Bundle().apply {
                    putString(BASE_CURRENCY, baseCurrency)
                }
            }
    }
}
