package me.hoyuo.myapplication.ui

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import me.hoyuo.myapplication.R
import me.hoyuo.myapplication.model.upbit.Market
import me.hoyuo.myapplication.model.upbit.Ticker
import me.hoyuo.myapplication.ui.coinlist.adapter.ItemAdapter
import me.hoyuo.myapplication.util.http.HttpClient
import me.hoyuo.myapplication.util.rx.DisposableManager
import timber.log.Timber

class MainActivity : FragmentActivity() {
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var httpClient: HttpClient
    private lateinit var disposableManager: DisposableManager
    private var krwList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setAdapter()
        disposableManager = DisposableManager()
        disposableManager.refreshIfNecessary()

        httpClient = HttpClient.getInstance()
    }

    override fun onStart() {
        super.onStart()
        disposableManager.refreshIfNecessary()

        if (krwList.isEmpty()) {
            disposableManager += httpClient.getMarketList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    ::getMarketListOnSuccess,
                    ::onError
                )
        } else {
            getTickers()
        }
    }

    override fun onStop() {
        super.onStop()
        disposableManager.dispose()
    }

    private fun onError(e: Throwable) {
        Timber.tag(TAG).e(e)
    }

    private fun getMarketListOnSuccess(list: List<Market>) {
        krwList.clear()
        list.filter { market -> market.market.contains("KRW-") }
            .map { krwList.add(it.market) }

        Timber.tag(TAG).d(krwList.joinToString(","))

        getTickers()
    }

    private fun getTickers() {
        disposableManager += httpClient.getTickers(krwList.joinToString(","))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                ::getTickersOnNext,
                ::onError
            )
    }

    private fun getTickersOnNext(list: List<Ticker>) {
        Timber.tag(TAG).d("getTickersOnNext - ${list.size}")
        itemAdapter.loadData(list)
    }

    private fun setAdapter() {
        itemAdapter = ItemAdapter().apply {
            setItemClickListener { ticker ->
                Timber.tag(TAG).d("Item Click")
                startActivity(CoinDetailActivity.newIntent(this@MainActivity.baseContext, ticker))
            }
        }

        itemListView.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }
}
