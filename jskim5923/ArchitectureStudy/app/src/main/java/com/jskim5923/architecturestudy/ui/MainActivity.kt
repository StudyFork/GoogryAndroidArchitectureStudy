package com.jskim5923.architecturestudy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jskim5923.architecturestudy.R
import com.jskim5923.architecturestudy.adapter.ViewPagerAdapter
import com.jskim5923.architecturestudy.api.ApiManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        private const val MARKET_DELIMITER = "-"
    }

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        compositeDisposable += ApiManager.coinApi.getMarketList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ marketList ->
                marketList.map {
                    it.market.substringBefore(MARKET_DELIMITER)
                }.distinct()
                    .run {
                        setViewPager(this)
                    }
            }, {
                it.printStackTrace()
            })
    }

    private fun setViewPager(titleList: List<String>) {
        tabLayout.setupWithViewPager(viewPager)
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, titleList)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}
