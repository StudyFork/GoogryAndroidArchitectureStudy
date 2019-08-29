package com.test.androidarchitecture

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.test.androidarchitecture.adpter.ViewPagerAdapter
import com.test.androidarchitecture.data.Market
import com.test.androidarchitecture.data.MarketTitle
import com.test.androidarchitecture.data.source.UpbitRepository
import com.test.androidarchitecture.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var mViewPagerAdapter: ViewPagerAdapter? = null
    private val upbitRepository by lazy { UpbitRepository }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_tabLayout.setupWithViewPager(main_viewPager)
        mViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        main_viewPager.offscreenPageLimit = 3
        main_viewPager.adapter = mViewPagerAdapter
        loadMarketData()
    }

    @SuppressLint("CheckResult")
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
    }

}