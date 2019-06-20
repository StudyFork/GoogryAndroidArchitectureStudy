package org.study.kotlin.androidarchitecturestudy.view.activity.main

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import org.study.kotlin.androidarchitecturestudy.R
import org.study.kotlin.androidarchitecturestudy.adapter.viewpageradapter.MainViewPagerAdapter
import org.study.kotlin.androidarchitecturestudy.view.fragment.main.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var viewPagerMain: ViewPager
    private lateinit var tablayoutMain: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("TAG MainActivity", "onCreate")

        initViews()
        setupViewPager()
    }


    private fun initViews() {
        Log.e("TAG MainActivity", "initViews")

        tablayoutMain = findViewById(R.id.tablayout_main)
        viewPagerMain = findViewById(R.id.viewpager_main)
    }

    private fun setupViewPager() {
        Log.e("TAG MainActivity", "setupViewPager")

        val adapter = MainViewPagerAdapter(
            supportFragmentManager
        )

        val firstFragment: MainFragment =
            MainFragment.createInstance("KRW")
        val secondFragment: MainFragment =
            MainFragment.createInstance("BTC")
        val thirdFragment: MainFragment =
            MainFragment.createInstance("ETH")
        val fourFragment: MainFragment =
            MainFragment.createInstance("USDT")

        adapter.addFragment(firstFragment, "KRW")
        adapter.addFragment(secondFragment, "BTC")
        adapter.addFragment(thirdFragment, "ETH")
        adapter.addFragment(fourFragment, "USDT")

        viewPagerMain.adapter = adapter
        tablayoutMain.setupWithViewPager(viewPagerMain)
    }


}

