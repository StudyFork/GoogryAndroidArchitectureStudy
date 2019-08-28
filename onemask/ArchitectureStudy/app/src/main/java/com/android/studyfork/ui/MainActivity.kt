package com.android.studyfork.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.studyfork.R
import com.android.studyfork.repository.UpbitRepositoryImpl
import com.android.studyfork.ui.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val viewPagerAdapter by lazy { ViewPagerAdapter(supportFragmentManager) }
    private val upbitRepository = UpbitRepositoryImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewPager()
        loadData()
    }

    @SuppressLint("CheckResult")
    private fun loadData() {
        upbitRepository.getMarketAll()
            .subscribe({

                val keys = it.keys.apply {
                    viewPagerAdapter.setTitles(this.toTypedArray())
                }
                val marketNamesArr = Array(keys.size) { "" }

                for((index : Int,value : String) in keys.withIndex()){
                    marketNamesArr[index] = it
                        .getValue(value)
                        .joinToString(","){it.market}
                }
                viewPagerAdapter.setData(marketNamesArr)
            },{
                Timber.e(it)
            })
    }

    private fun initViewPager() {
        with(pager_content){
            adapter = viewPagerAdapter
            layout_tab.setupWithViewPager(this)
            offscreenPageLimit= 3
        }
    }
}

