package com.android.studyfork.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.studyfork.R
import com.android.studyfork.network.api.UpbitService
import com.android.studyfork.repository.UpbitRepository
import com.android.studyfork.repository.UpbitRepositoryImpl
import com.android.studyfork.ui.adapter.ViewpagerAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val upbitService by lazy{ UpbitService.getInstance().upbitDataSource }
    private val viewpagerAdapter by lazy { ViewpagerAdapter(supportFragmentManager) }
    private lateinit var upbitRepository: UpbitRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        upbitRepository = UpbitRepositoryImpl(upbitService)
        initViewPager()
        loadData()
    }

    @SuppressLint("CheckResult")
    private fun loadData() {
        upbitRepository.getMarketAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                val keys = it.keys.apply {
                    viewpagerAdapter.setTitles(this.toTypedArray())
                }
                val marketNamesArr = Array(keys.size) { "" }

                for((index : Int,value : String) in keys.withIndex()){
                    marketNamesArr[index] = it
                        .getValue(value)
                        .joinToString(","){it.market}
                }
                viewpagerAdapter.setData(marketNamesArr)
            },{
                Timber.e("${it.printStackTrace()}")
            })
    }

    private fun initViewPager() {
        with(pager_content){
            adapter = viewpagerAdapter
            layout_tab.setupWithViewPager(this)
            offscreenPageLimit= 3
        }
    }
}

