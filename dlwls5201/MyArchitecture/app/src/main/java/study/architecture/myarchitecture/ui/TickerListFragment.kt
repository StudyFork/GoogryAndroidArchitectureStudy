package study.architecture.myarchitecture.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_ticker_list.*
import org.jetbrains.anko.support.v4.toast
import study.architecture.myarchitecture.BaseFragment
import study.architecture.myarchitecture.R
import study.architecture.myarchitecture.RxEventBus.RxEventBusHelper
import study.architecture.myarchitecture.data.ApiProvider
import study.architecture.myarchitecture.data.model.UpbitTicker
import study.architecture.myarchitecture.repository.UpbitRepository
import study.architecture.myarchitecture.repository.UpbitRepositoryImpl
import timber.log.Timber

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

    private lateinit var upbitRepository: UpbitRepository

    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_ticker_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        upbitRepository = UpbitRepositoryImpl(
            ApiProvider.provideUpbitApi()
        )

        subscribeEventBus()
        initRecyclerView()
        loadData()
    }

    override fun onDestroyView() {
        compositeDisposable.clear()
        super.onDestroyView()
    }

    private fun subscribeEventBus() {
        RxEventBusHelper.mSubject
            .subscribe {
                tickerAdapter.orderByField(it)
            }.also {
                compositeDisposable.add(it)
            }
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

        upbitRepository
            .getTickers(markets)
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

                Timber.d("$it")
                tickerAdapter.setItem(it.toMutableList())

            }) {
                Timber.e(it)
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