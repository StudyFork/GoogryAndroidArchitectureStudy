package org.study.kotlin.androidarchitecturestudy.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import org.study.kotlin.androidarchitecturestudy.R
import org.study.kotlin.androidarchitecturestudy.adapter.viewpageradapter.MainViewPagerAdapter
import org.study.kotlin.androidarchitecturestudy.ui.fragment.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var viewpager_main: ViewPager
    private lateinit var tablayout_main: TabLayout

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

        viewpager_main!!.adapter = adapter
        tablayout_main!!.setupWithViewPager(viewpager_main)

    }


}