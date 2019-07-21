package com.android.studyfork.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.studyfork.R
import com.android.studyfork.repository.UpbitApi
import com.android.studyfork.repository.UpbitService
import com.android.studyfork.repository.model.MarketAllResponse
import com.android.studyfork.ui.adpater.ViewpagerAdapter
import com.google.android.material.tabs.TabLayout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    lateinit var upbitService: UpbitApi
    lateinit var viewpagerAdapter: ViewpagerAdapter

    private var allMarketList:List<MarketAllResponse>  = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupTab()
        setupApiService()
        loadData()
    }

    @SuppressLint("CheckResult")
    private fun loadData() {
        upbitService.getMarketAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let {
                    allMarketList = it
               }
                setViewPager()
            },{
                Timber.e("${it.printStackTrace()}")
            })
    }

    private fun setupApiService() {
        upbitService = UpbitService.getInstance().upbitApi
    }


    private fun setupTab() {
        layout_tab.addTab(layout_tab.newTab().setText("KRW"))
        layout_tab.addTab(layout_tab.newTab().setText("BTC"))
        layout_tab.addTab(layout_tab.newTab().setText("ETH"))
        layout_tab.addTab(layout_tab.newTab().setText("USDT"))
    }

    private fun setViewPager() {
        viewpagerAdapter = ViewpagerAdapter(supportFragmentManager,allMarketList,layout_tab.tabCount)
        pager_content.adapter = viewpagerAdapter
        layout_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabSelected(tab: TabLayout.Tab) {
                pager_content.currentItem=(tab.position)
            }
        })
    }


}

