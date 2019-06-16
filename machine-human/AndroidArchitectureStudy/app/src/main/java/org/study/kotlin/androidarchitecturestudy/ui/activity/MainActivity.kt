package org.study.kotlin.androidarchitecturestudy.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import org.study.kotlin.androidarchitecturestudy.R
import org.study.kotlin.androidarchitecturestudy.adapter.viewpageradapter.MainViewPagerAdapter
import org.study.kotlin.androidarchitecturestudy.ui.fragment.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var viewPagerMain: ViewPager
    private lateinit var tablayoutMain: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        setupViewPager()
    }


    private fun initViews() {
        tablayoutMain = findViewById(R.id.tablayout_main)
        viewPagerMain = findViewById(R.id.viewpager_main)

    }

    private fun setupViewPager() {

        val adapter = MainViewPagerAdapter(
            supportFragmentManager
        )

        val firstFragment: MainFragment = MainFragment.createInstance("KRW")
        val secondFragment: MainFragment = MainFragment.createInstance("BTC")
        val thirdFragment: MainFragment = MainFragment.createInstance("ETH")
        val fourFragment: MainFragment = MainFragment.createInstance("USDT")

        adapter.addFragment(firstFragment, "KRW")
        adapter.addFragment(secondFragment, "BTC")
        adapter.addFragment(thirdFragment, "ETH")
        adapter.addFragment(fourFragment, "USDT")

        viewPagerMain.adapter = adapter
        tablayoutMain.setupWithViewPager(viewPagerMain)

    }


}

