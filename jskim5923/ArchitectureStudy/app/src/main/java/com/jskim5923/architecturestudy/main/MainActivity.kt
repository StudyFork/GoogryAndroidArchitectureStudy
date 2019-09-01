package com.jskim5923.architecturestudy.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jskim5923.architecturestudy.R
import com.jskim5923.architecturestudy.adapter.ViewPagerAdapter
import com.jskim5923.architecturestudy.extension.getCoinCurrency
import com.jskim5923.architecturestudy.model.data.source.Repository
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
        compositeDisposable += Repository.getMarketList()
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
