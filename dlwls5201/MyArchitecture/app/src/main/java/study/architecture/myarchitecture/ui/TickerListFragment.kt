package study.architecture.myarchitecture.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_ticker_list.*
import org.jetbrains.anko.support.v4.toast
import study.architecture.myarchitecture.BaseFragment
import study.architecture.myarchitecture.R
import study.architecture.myarchitecture.network.ApiProvider
import study.architecture.myarchitecture.network.model.UpbitTicker
import study.architecture.myarchitecture.util.Dlog

class TickerListFragment : BaseFragment() {

    companion object {

        const val KEY_MARKETS = "markets"

        fun newInstance(tickers: String) = TickerListFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_MARKETS, tickers)
            }
        }
    }

    private val tickerAdapter = TickerAdapter()

    private val upbitApi = ApiProvider.provideUpbitApi()

    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_ticker_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        loadData()
    }

    private fun initRecyclerView() {

        with(rvTickerList) {
            layoutManager = LinearLayoutManager(context)
            adapter = tickerAdapter
        }

        tickerAdapter.setTickerClickListener(object : TickerAdapter.TickerClickListener {
            override fun onItemClick(ticker: UpbitTicker) {
                toast(ticker.toString())
            }
        })
    }

    private fun loadData() {

        val markets = arguments?.getString(KEY_MARKETS) ?: ""

        upbitApi
            .getTickers(markets)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                showProgress()
            }
            .doOnSuccess {
                hideProgress()
            }
            .doOnError {
                hideProgress()
            }
            .subscribe({

                Dlog.d("$it")
                tickerAdapter.setItem(it.toMutableList())

            }){
                Dlog.e(it.message)
            }.also {
                compositeDisposable.add(it)
            }
    }

    private fun showProgress() {
        pbTickerList.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        pbTickerList.visibility = View.GONE
    }
}