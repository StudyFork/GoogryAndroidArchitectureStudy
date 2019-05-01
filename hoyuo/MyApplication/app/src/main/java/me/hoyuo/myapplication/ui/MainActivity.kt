package me.hoyuo.myapplication.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.itemListView
import me.hoyuo.myapplication.R
import me.hoyuo.myapplication.model.upbit.Market
import me.hoyuo.myapplication.model.upbit.Ticker
import me.hoyuo.myapplication.ui.adapter.ItemAdapter
import me.hoyuo.myapplication.util.http.HttpClient
import me.hoyuo.myapplication.util.rx.DisposableManager
import timber.log.Timber

class MainActivity : AppCompatActivity() {
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

        httpClient = HttpClient.getInstance(this)
    }

    override fun onStart() {
        super.onStart()
        disposableManager.refreshIfNecessary()
        disposableManager += httpClient.getMarketList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        ::getMarketListOnSuccess,
                        ::onError
                )
    }

    override fun onStop() {
        super.onStop()
        disposableManager.dispose()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun onError(e: Throwable) {
        Timber.tag(TAG).e(e)
    }

    private fun getMarketListOnSuccess(t: List<Market>) {
        krwList.clear()
        t.forEach { market ->
            if (market.market.contains("KRW-")) {
                krwList.add(market.market)
            }
        }

        Timber.tag(TAG).d(krwList.joinToString(","))

        disposableManager += httpClient.getTickers(krwList.joinToString(","))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        ::getTickersOnSuccess,
                        ::onError
                )
    }

    private fun getTickersOnSuccess(list: List<Ticker>) {
        itemAdapter.loadData(list)
    }

    private fun setAdapter() {
        itemAdapter = ItemAdapter().apply {
            setItemClickListener(object : ItemAdapter.ItemClickListener {
                override fun onItemClick(ticker: Ticker) {
                    Timber.d("Item Click")

                }
            })
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
