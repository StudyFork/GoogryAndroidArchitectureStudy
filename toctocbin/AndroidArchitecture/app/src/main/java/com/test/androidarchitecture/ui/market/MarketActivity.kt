package com.test.androidarchitecture.ui.market

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.test.androidarchitecture.R
import com.test.androidarchitecture.adpter.ViewPagerAdapter
import com.test.androidarchitecture.data.MarketTitle
import com.test.androidarchitecture.data.source.UpbitRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_market.*

class MarketActivity : AppCompatActivity() {


    private var mViewPagerAdapter: ViewPagerAdapter? = null
    private val upbitRepository by lazy { UpbitRepository }
    private val disposables by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market)
        main_tabLayout.setupWithViewPager(main_viewPager)
        mViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        with(main_viewPager) {
            offscreenPageLimit = 3
            adapter = mViewPagerAdapter
        }
        loadMarketData()
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }

    private fun loadMarketData() {
        upbitRepository.getMarketAll()
            .map { list ->
                list.groupBy { it.market.substringBefore("-") }
                    .asSequence()
                    .map { (key, value) ->
                        MarketTitle(
                            marketTitle = key,
                            marketSearch = value.joinToString() { it.market }
                        )
                    }
                    .toList()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mViewPagerAdapter?.setData(it)
                }, {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            )
            .apply { disposables.add(this) }
    }

}