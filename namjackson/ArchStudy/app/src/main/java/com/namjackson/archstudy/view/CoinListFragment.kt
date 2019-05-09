package com.namjackson.archstudy.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.namjackson.archstudy.CoinListContract
import com.namjackson.archstudy.CoinListPresenter
import com.namjackson.archstudy.R
import com.namjackson.archstudy.data.Ticker
import com.namjackson.archstudy.data.repository.UpbitRepository
import com.namjackson.archstudy.databinding.FragmentCoinListBinding
import com.namjackson.archstudy.network.UpbitService
import com.namjackson.archstudy.view.adapter.CoinListAdapter
import java.util.*


private const val BASE_CURRENCY = "BASE_CURRENCY"
private const val SECOND = 1000L

class CoinListFragment : Fragment(), CoinListContract.View {


    override lateinit var presenter: CoinListContract.Presenter
    private lateinit var adapter: CoinListAdapter
    private lateinit var timer: Timer

    private lateinit var binding: FragmentCoinListBinding


    override fun showCoinList(ticker: List<Ticker>) {
        adapter.setList(ticker)
    }

    override fun setProgress(boolean: Boolean) {
        if (boolean) {
            showProgress()
        } else {
            hideProgress()
        }
    }

    override fun showError(errorMsg: String) {
        Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_coin_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = CoinListPresenter(UpbitRepository.getInstance(UpbitService.upbitApi), this)
        arguments?.let {
            presenter.baseCurrency = it.getString(BASE_CURRENCY) ?: ""
        }
        initLayout()
        initTimer()
    }


    fun initLayout() {
        adapter = CoinListAdapter()
        binding.recyclerView.adapter = this.adapter
    }

    fun initTimer() {
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                presenter.loadCoinList()
            }
        }, 0, (20 * SECOND))
    }

    override fun onDestroyView() {
        timer.cancel()
        super.onDestroyView()
    }

    companion object {
        fun newInstance(baseCurrency: String) =
            CoinListFragment().apply {
                arguments = Bundle().apply {
                    putString(BASE_CURRENCY, baseCurrency)
                }
            }
    }

    fun showProgress() {
        binding.progress.visibility = View.VISIBLE
    }

    fun hideProgress() {
        binding.progress.visibility = View.GONE
    }

}
