package com.jskim5923.architecturestudy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jskim5923.architecturestudy.R
import com.jskim5923.architecturestudy.adapter.ViewPagerAdapter
import com.jskim5923.architecturestudy.api.ApiManager
import com.jskim5923.architecturestudy.extension.getCoinCurrency
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val compositeDisposable = CompositeDisposable()

    private val viewpagerAdapter by lazy {
        ViewPagerAdapter(supportFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(viewPager) {
            tabLayout.setupWithViewPager(this)
            adapter = viewpagerAdapter
        }

        loadData()
    }

    private fun loadData() {
        compositeDisposable += ApiManager.coinApi.getMarketList()
            .subscribeOn(Schedulers.io())
            .map { marketList ->
                marketList.map {
                    it.market.getCoinCurrency()
                }.distinct()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewpagerAdapter.updateItem(it)
            }, {
                it.printStackTrace()
            })
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}
